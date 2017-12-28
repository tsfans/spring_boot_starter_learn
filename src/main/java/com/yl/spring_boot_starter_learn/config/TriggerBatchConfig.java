package com.yl.spring_boot_starter_learn.config;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.validator.Validator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.PlatformTransactionManager;

import com.yl.spring_boot_starter_learn.batch.CsVBeanValidator;
import com.yl.spring_boot_starter_learn.batch.CsvItemProcessor;
import com.yl.spring_boot_starter_learn.batch.CsvJobListener;
import com.yl.spring_boot_starter_learn.domain.People;

@Configuration
@EnableBatchProcessing
public class TriggerBatchConfig {

	@Bean
	public CsvJobListener csvJobListener() {
		return new CsvJobListener();
	}
	
	@Bean
	public Validator<People> csVBeanValidator() {
		return new CsVBeanValidator<People>();
	}

	@Bean
	@StepScope
	public FlatFileItemReader<People> reader(
			@Value("#{jobParameters['input.file.name']}")String pathToFile){
		FlatFileItemReader<People> reader = new FlatFileItemReader<People>();
		reader.setResource(new ClassPathResource("people.csv"));
		reader.setLineMapper(new DefaultLineMapper<People>() {{
			setLineTokenizer(new DelimitedLineTokenizer() {{
				setNames(new String[] {"name","age","nation","address"});
			}});
			setFieldSetMapper(new BeanWrapperFieldSetMapper<People>() {{
				setTargetType(People.class);
			}});
		}});
		return reader;
	}
	@Bean
	public ItemProcessor<People,People> processor(){
		CsvItemProcessor processor = new CsvItemProcessor();
		processor.setValidator(csVBeanValidator());
		return processor;
	}
	@Bean
	public ItemWriter<People> writer(DataSource dataSource){
		JdbcBatchItemWriter<People> writer = new JdbcBatchItemWriter<People>();
		writer.setItemSqlParameterSourceProvider(
				new BeanPropertyItemSqlParameterSourceProvider<People>());
		String sql = "insert into PEOPLE "+"(id,name,age,nation,address)"+
				"values(hibernate_sequence.nextval, :name, :age, :nation, :address)";
		writer.setSql(sql);
		writer.setDataSource(dataSource);
		return writer;
	}
	@Bean
	public JobRepository jobRepository(DataSource dataSource,PlatformTransactionManager transactionManager) throws Exception {
		JobRepositoryFactoryBean jobRepositoryFactoryBean = new JobRepositoryFactoryBean();
		jobRepositoryFactoryBean.setDatabaseType("oracle");
		jobRepositoryFactoryBean.setDataSource(dataSource);
		jobRepositoryFactoryBean.setTransactionManager(transactionManager);
		return jobRepositoryFactoryBean.getObject();
	}
	@Bean
	public SimpleJobLauncher jobLauncher(DataSource dataSource,PlatformTransactionManager transactionManager) throws Exception {
		SimpleJobLauncher simpleJobLauncher =new SimpleJobLauncher();
		simpleJobLauncher.setJobRepository(jobRepository(dataSource,transactionManager));
		return simpleJobLauncher;
	}
	@Bean
	public Job importJob(JobBuilderFactory jobs,Step s1) {
		return jobs.get("importJob")
				.incrementer(new RunIdIncrementer())
				.flow(s1)
				.end()
				.listener(csvJobListener())
				.build();
	}
	@Bean
	public Step step(StepBuilderFactory stepBuilderFactory,ItemReader<People> reader
			,ItemWriter<People> writer
			,ItemProcessor<People,People> processor) {
		return stepBuilderFactory.get("step1")
				.<People,People>chunk(65000)
				.reader(reader)
				.processor(processor)
				.writer(writer)
				.build();
	}
}
