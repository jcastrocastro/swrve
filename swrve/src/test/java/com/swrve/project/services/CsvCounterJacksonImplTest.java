package com.swrve.project.services;

import static org.junit.Assert.assertEquals;

import java.io.File;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvParser.Feature;
import com.swrve.project.exceptions.SwrveException;
import com.swrve.project.model.ProgramOutput;

/**
 * @author Raul Castro
 */
public class CsvCounterJacksonImplTest {

    private CsvMapper mapper;

    @Before
    public void setUp() {
        mapper = new CsvMapper();
        mapper = mapper
            .enable(Feature.WRAP_AS_ARRAY)
            .enable(Feature.SKIP_EMPTY_LINES)
            .enable(Feature.INSERT_NULLS_FOR_MISSING_COLUMNS)
            .enable(Feature.TRIM_SPACES);
        mapper.configure(JsonParser.Feature.ALLOW_YAML_COMMENTS, true);
    }

    @Test
    public void printInfoFromCsv_dataWithHeader() throws SwrveException {
        String toFile = getClass().getResource("/data_with_header.csv").getFile();
        File file = new File(toFile);
        CsvCounterJacksonImpl csvCounterJacksonImpl = new CsvCounterJacksonImpl(file, mapper);
        ProgramOutput programOutput = csvCounterJacksonImpl.printInfoFromCsv();

        assertEquals(3, programOutput.getTotalCountUsers());
        assertEquals(3, programOutput.getTotalSpend());
        assertEquals(2, programOutput.getNumberUsersWithStandardResolution());
        assertEquals("da27c392de83933dbde844d580e9fa60", programOutput.getFirstUserId());
    }

    @Test
    public void printInfoFromCsv_dataWithHeaderOneColumn() throws SwrveException {
        String toFile = getClass().getResource("/data_with_header_one_column.csv").getFile();
        File file = new File(toFile);
        CsvCounterJacksonImpl csvCounterJacksonImpl = new CsvCounterJacksonImpl(file, mapper);
        ProgramOutput programOutput = csvCounterJacksonImpl.printInfoFromCsv();

        assertEquals(1, programOutput.getTotalCountUsers());
        assertEquals(1, programOutput.getTotalSpend());
        assertEquals(1, programOutput.getNumberUsersWithStandardResolution());
        assertEquals("da27c392de83933dbde844d580e9fa60", programOutput.getFirstUserId());
    }

    @Test
    public void printInfoFromCsv_dataWithHeaderAndComments() throws SwrveException {
        String toFile = getClass().getResource("/data_with_header_and_comments.csv").getFile();
        File file = new File(toFile);
        CsvCounterJacksonImpl csvCounterJacksonImpl = new CsvCounterJacksonImpl(file, mapper);
        ProgramOutput programOutput = csvCounterJacksonImpl.printInfoFromCsv();

        assertEquals(1, programOutput.getTotalCountUsers());
        assertEquals(1, programOutput.getTotalSpend());
        assertEquals(1, programOutput.getNumberUsersWithStandardResolution());
        assertEquals("da27c392de83933dbde844d580e9fa60", programOutput.getFirstUserId());
    }

    @Test
    public void printInfoFromCsv_dataDifferentLength() throws SwrveException {
        String toFile = getClass().getResource("/data_with_header_different_length.csv").getFile();
        File file = new File(toFile);
        CsvCounterJacksonImpl csvCounterJacksonImpl = new CsvCounterJacksonImpl(file, mapper);
        ProgramOutput programOutput = csvCounterJacksonImpl.printInfoFromCsv();

        assertEquals(1, programOutput.getTotalCountUsers());
        assertEquals(1, programOutput.getTotalSpend());
        assertEquals(1, programOutput.getNumberUsersWithStandardResolution());
        assertEquals("fa409818ec81dc570fd63f5f776be2c3", programOutput.getFirstUserId());
    }

    @Test
    public void printInfoFromCsv_linesWithDoubleQuotas() throws SwrveException {
        String toFile = getClass().getResource("/data_with_header_double_quotas.csv").getFile();
        File file = new File(toFile);
        CsvCounterJacksonImpl csvCounterJacksonImpl = new CsvCounterJacksonImpl(file, mapper);
        ProgramOutput programOutput = csvCounterJacksonImpl.printInfoFromCsv();

        assertEquals(3, programOutput.getTotalCountUsers());
        assertEquals(3, programOutput.getTotalSpend());
        assertEquals(2, programOutput.getNumberUsersWithStandardResolution());
        assertEquals("da27c392de83933dbde844d580e9fa60", programOutput.getFirstUserId());
    }

    @Test
    public void printInfoFromCsv_emptyField() throws SwrveException {
        String toFile = getClass().getResource("/data_with_header_empty_field.csv").getFile();
        File file = new File(toFile);
        CsvCounterJacksonImpl csvCounterJacksonImpl = new CsvCounterJacksonImpl(file, mapper);
        ProgramOutput programOutput = csvCounterJacksonImpl.printInfoFromCsv();

        assertEquals(3, programOutput.getTotalCountUsers());
        assertEquals(2, programOutput.getTotalSpend());
        assertEquals(1, programOutput.getNumberUsersWithStandardResolution());
        assertEquals("da27c392de83933dbde844d580e9fa60", programOutput.getFirstUserId());
    }

    @Test
    public void printInfoFromCsv_emptyLines() throws SwrveException {
        String toFile = getClass().getResource("/data_with_header_empty_lines.csv").getFile();
        File file = new File(toFile);
        CsvCounterJacksonImpl csvCounterJacksonImpl = new CsvCounterJacksonImpl(file, mapper);
        ProgramOutput programOutput = csvCounterJacksonImpl.printInfoFromCsv();

        assertEquals(3, programOutput.getTotalCountUsers());
        assertEquals(3, programOutput.getTotalSpend());
        assertEquals(2, programOutput.getNumberUsersWithStandardResolution());
        assertEquals("da27c392de83933dbde844d580e9fa60", programOutput.getFirstUserId());
    }

    @Test
    public void printInfoFromCsv_withWhitespacesInsideField() throws SwrveException {
        String toFile = getClass().getResource("/data_with_header_whitespaces.csv").getFile();
        File file = new File(toFile);
        CsvCounterJacksonImpl csvCounterJacksonImpl = new CsvCounterJacksonImpl(file, mapper);
        ProgramOutput programOutput = csvCounterJacksonImpl.printInfoFromCsv();

        assertEquals(3, programOutput.getTotalCountUsers());
        assertEquals(12, programOutput.getTotalSpend());
        assertEquals(2, programOutput.getNumberUsersWithStandardResolution());
        assertEquals("da27c392de83933dbd e844d580e9fa60 ", programOutput.getFirstUserId());
    }

    @Test
    public void printInfoFromCsv_withoutHeader() throws SwrveException {
        String toFile = getClass().getResource("/data_without_header.csv").getFile();
        File file = new File(toFile);
        CsvCounterJacksonImpl csvCounterJacksonImpl = new CsvCounterJacksonImpl(file, mapper);
        ProgramOutput programOutput = csvCounterJacksonImpl.printInfoFromCsv();

        assertEquals(3, programOutput.getTotalCountUsers());
        assertEquals(3, programOutput.getTotalSpend());
        assertEquals(2, programOutput.getNumberUsersWithStandardResolution());
        assertEquals("da27c392de83933dbde844d580e9fa60", programOutput.getFirstUserId());
    }

    @Test
    public void printInfoFromCsv_withSpecialCharacter() throws SwrveException {
        String toFile = getClass().getResource("/data_with_header_with_special_character.csv").getFile();
        File file = new File(toFile);
        CsvCounterJacksonImpl csvCounterJacksonImpl = new CsvCounterJacksonImpl(file, mapper);
        ProgramOutput programOutput = csvCounterJacksonImpl.printInfoFromCsv();

        assertEquals(1, programOutput.getTotalCountUsers());
        assertEquals(1, programOutput.getTotalSpend());
        assertEquals(1, programOutput.getNumberUsersWithStandardResolution());
        assertEquals("da27c392de83933,dbde\"844d580e9fa60", programOutput.getFirstUserId());
    }

    @Test
    public void printInfoFromCsv_emptyFileNoHeader() throws SwrveException {
        String toFile = getClass().getResource("/empty_file_no_header.csv").getFile();
        File file = new File(toFile);
        CsvCounterJacksonImpl csvCounterJacksonImpl = new CsvCounterJacksonImpl(file, mapper);
        ProgramOutput programOutput = csvCounterJacksonImpl.printInfoFromCsv();

        assertEquals(0, programOutput.getTotalCountUsers());
        assertEquals(0, programOutput.getTotalSpend());
        assertEquals(0, programOutput.getNumberUsersWithStandardResolution());
        assertEquals(null, programOutput.getFirstUserId());
    }

    @Test
    public void printInfoFromCsv_emptyFileWithHeader() throws SwrveException {
        String toFile = getClass().getResource("/empty_file_with_header.csv").getFile();
        File file = new File(toFile);
        CsvCounterJacksonImpl csvCounterJacksonImpl = new CsvCounterJacksonImpl(file, mapper);
        ProgramOutput programOutput = csvCounterJacksonImpl.printInfoFromCsv();

        assertEquals(0, programOutput.getTotalCountUsers());
        assertEquals(0, programOutput.getTotalSpend());
        assertEquals(0, programOutput.getNumberUsersWithStandardResolution());
        assertEquals(null, programOutput.getFirstUserId());
    }

    @Ignore
    @Test(expected = SwrveException.class)
    public void printInfoFromCsv_noExistFileName() throws SwrveException {
        File file = new File("/no_exist_file");
        CsvCounterJacksonImpl csvCounterJacksonImpl = new CsvCounterJacksonImpl(file, mapper);
        ProgramOutput programOutput = csvCounterJacksonImpl.printInfoFromCsv();
    }

    @Ignore
    @Test(expected = SwrveException.class)
    public void printInfoFromCsv_nullFileName() throws SwrveException {
        CsvCounterJacksonImpl csvCounterJacksonImpl = new CsvCounterJacksonImpl(null, mapper);
        ProgramOutput programOutput = csvCounterJacksonImpl.printInfoFromCsv();
    }
}
