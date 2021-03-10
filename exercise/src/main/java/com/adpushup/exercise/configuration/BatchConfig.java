package com.adpushup.exercise.configuration;

import com.adpushup.exercise.model.Website;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

    @Value("classpath:input/WEBSITE*.csv")
    // Uncomment to read files from c:drive
    //@Value("file:C:\\input_data_small/WEBSITE*.csv")
    private Resource[] inputResources;

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private DbWriter dbWriter;

    @Bean
    public Job readCSVFilesJob() {
        return jobBuilderFactory
                .get("readCSVFilesJob")
                .incrementer(new RunIdIncrementer())
                .start(step1())
                .build();
    }

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1").<Website, Website>chunk(5)
                .reader(multiResourceItemReader())
                //.writer(writer())
                .writer(dbWriter)
                .build();
    }

    @Bean
    public MultiResourceItemReader<Website> multiResourceItemReader()
    {
        MultiResourceItemReader<Website> resourceItemReader = new MultiResourceItemReader();
        resourceItemReader.setResources(inputResources);
        resourceItemReader.setDelegate(reader());
        return resourceItemReader;
    }

    @Bean
    public FlatFileItemReader<Website> reader()
    {
        FlatFileItemReader<Website> reader = new FlatFileItemReader<Website>();
        reader.setLinesToSkip(1);
        reader.setLineMapper(new DefaultLineMapper() {
            {
                setLineTokenizer(new DelimitedLineTokenizer() {
                    {
                        setNames(new String[] {"AdvertiserID", "Revenue"});
                    }
                });
                setFieldSetMapper(new BeanWrapperFieldSetMapper<Website>() {
                    {
                        setTargetType(Website.class);
                    }
                });
            }
        });
        return reader;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}
