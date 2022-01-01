package com.swrve.project.services;

import java.io.File;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.swrve.project.exceptions.SwrveException;
import com.swrve.project.model.ProgramOutput;

/**
 *
 * @author Raul Castro
 */
public class CsvCounterJacksonImpl implements CsvCounter {

    private static Logger logger = LoggerFactory.getLogger(CsvCounterJacksonImpl.class);

    private static final int NUMBER_COLS = 6;
    private static final String STANDARD_WIDTH = "640";
    private static final String STANDARD_HEIGHT = "960";
    private static final String FIRST_COLUMN_NAME = "user_id";

    private final File file;
    private final CsvMapper mapper;

    public CsvCounterJacksonImpl(File file, CsvMapper mapper) {
        this.file = file;
        this.mapper = mapper;
    }

    public ProgramOutput printInfoFromCsv() throws SwrveException {
        ProgramOutput programOutput = new ProgramOutput();

        try {
            MappingIterator<String[]> iterator = mapper.readerFor(String[].class).readValues(file);

            // Skip header if any
            if (iterator.hasNext()) {
                String[] row = iterator.next();
                if (!existHeader(row) && isFixedNumberColumns(row)) {
                    updateOutputValues(row, programOutput);
                }
            }

            while (iterator.hasNext()) {
                String[] row = iterator.next();
                if (isFixedNumberColumns(row)) {
                    updateOutputValues(row, programOutput);
                }
            }

            printOutput(programOutput);
        } catch (Exception exc) {
            logger.error("Error formatting file {}", file!=null ? file.getName() : "");
            throw new SwrveException("Error formatting file", exc);
        }

        return programOutput;
    }

    private boolean isFixedNumberColumns(String[] row) {
        return NUMBER_COLS == row.length;
    }

    private boolean existHeader(String[] row) {
        return row.length > 0 && FIRST_COLUMN_NAME.equals(row[0]);
    }

    private void updateOutputValues(String[] row, ProgramOutput programOutput) {
        if (StringUtils.isEmpty(programOutput.getFirstUserId())) {
            String userId = row[0];
            programOutput.setFirstUserId(userId);
        }

        programOutput.setTotalCountUsers(programOutput.getTotalCountUsers() + 1);

        // Guessing here that the amount in an long value. The test spec is not clear about types
        long spendValue = 0;
        String spend = row[2];
        if (!StringUtils.isEmpty(spend)) {
            spendValue = Long.valueOf(spend);
        }
        programOutput.setTotalSpend(programOutput.getTotalSpend() + spendValue);

        String deviceHeight = row[4];
        String deviceWidth = row[5];
        if (isStandardResolution(deviceHeight, deviceWidth)) {
            programOutput.setNumberUsersWithStandardResolution(programOutput.getNumberUsersWithStandardResolution() + 1);
        }
    }

    private boolean isStandardResolution(String deviceHeight, String deviceWidth) {
        return STANDARD_HEIGHT.equals(deviceHeight) && STANDARD_WIDTH.equals(deviceWidth);
    }

    private void printOutput(ProgramOutput programOutputDto) {
        // I consider System.out.println a bad practise but the problem spec is asking for this output. It's poorly flexible.
        // It's preferable to print info in logs using appender or send the object to the UI to be shown to the user.
        System.out.println(programOutputDto.getTotalCountUsers());
        System.out.println(programOutputDto.getNumberUsersWithStandardResolution());
        System.out.println(programOutputDto.getTotalSpend());
        System.out.println(programOutputDto.getFirstUserId());
    }
}
