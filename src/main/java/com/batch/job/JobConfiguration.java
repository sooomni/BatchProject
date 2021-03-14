package com.batch.job;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@RequiredArgsConstructor // 간편하게 생성자 주입을 위한 Lombok 사용

@Configuration // 모든 Job은 @Configuration으로 등록해서 사용
public class JobConfiguration {

    private final String BATCH_NAME = "FirstJob";

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job startJob() {
        return jobBuilderFactory.get(BATCH_NAME)  //Builder를 통해 이름을 지정
                .start(startStep1())
                .build();
    }

    @Bean
    public Step startStep1() {
        return stepBuilderFactory.get(BATCH_NAME + " Step1")  //Builder를 통해 이름을 지정
                .tasklet((contribution, chunkContext) -> {  //Step 안의 기능 명시
                    log.info(">>>>> This is Step1");   //기능 : log 출력
                    return RepeatStatus.FINISHED;
                })
                .build();
    }

}
