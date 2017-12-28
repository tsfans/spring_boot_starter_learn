package com.yl.spring_boot_starter_learn.batch;

import org.springframework.batch.item.validator.ValidatingItemProcessor;
import org.springframework.batch.item.validator.ValidationException;

import com.yl.spring_boot_starter_learn.domain.People;

public class CsvItemProcessor extends ValidatingItemProcessor<People>{

	@Override
	public People process(People item) throws ValidationException {
		super.process(item);
		if(item.getNation().equals("汉族")) {
			item.setNation("01");
		}else if(item.getNation().equals("苗族")) {
			item.setNation("02");
		}else {
			item.setNation("03");
		}
		return item;
	}
}
