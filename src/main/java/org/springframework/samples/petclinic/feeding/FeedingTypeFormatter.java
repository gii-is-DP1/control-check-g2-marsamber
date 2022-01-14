package org.springframework.samples.petclinic.feeding;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

@Component
public class FeedingTypeFormatter implements Formatter<FeedingType>{
    private final FeedingService feedingService;

	@Autowired
	public FeedingTypeFormatter(FeedingService feedingService) {
		this.feedingService = feedingService;
	}
	

    @Override
    public String print(FeedingType object, Locale locale) {
        return object.getName();
    }

    @Override
    public FeedingType parse(String text, Locale locale) throws ParseException {
        FeedingType feedingType = feedingService.getFeedingType(text);
    	if(feedingType != null) {
    		return feedingType;
    	}else {
    		throw new ParseException("Feeding type not found: " + text, 0);
    	}
    }
    
}
