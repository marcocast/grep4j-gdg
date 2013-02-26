package com.gdg.grep4j.demo;

import static com.gdg.grep4j.demo.profiles.LocalProfiles.consumer1;
import static com.gdg.grep4j.demo.profiles.LocalProfiles.consumer2;
import static com.gdg.grep4j.demo.profiles.LocalProfiles.consumer3;
import static com.gdg.grep4j.demo.profiles.LocalProfiles.consumer4;
import static com.gdg.grep4j.demo.profiles.LocalProfiles.consumer5;
import static org.grep4j.core.Grep4j.constantExpression;
import static org.grep4j.core.Grep4j.grep;
import static org.grep4j.core.fluent.Dictionary.on;
import static org.grep4j.core.fluent.Dictionary.option;
import static org.grep4j.core.fluent.Dictionary.with;
import static org.grep4j.core.options.Option.ignoreCase;

import org.grep4j.core.result.GrepResult;
import org.grep4j.core.result.GrepResults;

public class FreeGrepTest {

	public static void main(String[] args) {
		GrepResults allProfilesResults = grep(constantExpression("ERROR_UNRECOVERABLE"),
				on(consumer1, consumer2, consumer3, consumer4, consumer5),
				with(option(ignoreCase())));
		
		System.out.println("Total lines found : " + allProfilesResults.totalLines());
		for (GrepResult grepResult : allProfilesResults) {
			System.out.println("***************["+grepResult.getProfileName()+", total lines : "+grepResult.totalLines()+"]********");
			System.out.println(grepResult);
		}
	}

}
