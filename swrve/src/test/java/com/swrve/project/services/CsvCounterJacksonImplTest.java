package com.swrve.project.services;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvParser.Feature;
import com.swrve.project.exceptions.SwrveException;
import com.swrve.project.model.ProgramOutput;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import static org.junit.Assert.assertEquals;

/**
 * @author Raul Castro
 */
public class CsvCounterJacksonImplTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(CsvCounterJacksonImplTest.class);

    private final ClassLoader classloader = Thread.currentThread().getContextClassLoader();
    private CsvMapper mapper;

    @Before
    public void setUp() {
        mapper = new CsvMapper();
        mapper = mapper.enable(Feature.WRAP_AS_ARRAY)
                .enable(Feature.SKIP_EMPTY_LINES)
                .enable(Feature.INSERT_NULLS_FOR_MISSING_COLUMNS)
                .enable(Feature.TRIM_SPACES);
        mapper.configure(JsonParser.Feature.ALLOW_YAML_COMMENTS, true);
    }

    @Test
    public void printInfoFromCsv_dataWithHeader() throws SwrveException {
        File file = toFile("data_with_header.csv");
        CsvCounterJacksonImpl csvCounterJacksonImpl = new CsvCounterJacksonImpl(file, mapper);
        ProgramOutput programOutput = csvCounterJacksonImpl.printInfoFromCsv();

        assertEquals(3, programOutput.getTotalCountUsers());
        assertEquals(3, programOutput.getTotalSpend());
        assertEquals(2, programOutput.getNumberUsersWithStandardResolution());
        assertEquals("da27c392de83933dbde844d580e9fa60", programOutput.getFirstUserId());
    }

    @Test
    public void printInfoFromCsv_dataWithHeaderOneColumn() throws SwrveException {
        File file = toFile("data_with_header_one_column.csv");
        CsvCounterJacksonImpl csvCounterJacksonImpl = new CsvCounterJacksonImpl(file, mapper);
        ProgramOutput programOutput = csvCounterJacksonImpl.printInfoFromCsv();

        assertEquals(1, programOutput.getTotalCountUsers());
        assertEquals(1, programOutput.getTotalSpend());
        assertEquals(1, programOutput.getNumberUsersWithStandardResolution());
        assertEquals("da27c392de83933dbde844d580e9fa60", programOutput.getFirstUserId());
    }

    @Test
    public void printInfoFromCsv_dataWithHeaderAndComments() throws SwrveException {
        File file = toFile("data_with_header_and_comments.csv");
        CsvCounterJacksonImpl csvCounterJacksonImpl = new CsvCounterJacksonImpl(file, mapper);
        ProgramOutput programOutput = csvCounterJacksonImpl.printInfoFromCsv();

        assertEquals(1, programOutput.getTotalCountUsers());
        assertEquals(1, programOutput.getTotalSpend());
        assertEquals(1, programOutput.getNumberUsersWithStandardResolution());
        assertEquals("da27c392de83933dbde844d580e9fa60", programOutput.getFirstUserId());
    }

    @Test
    public void printInfoFromCsv_dataDifferentLength() throws SwrveException {
        File file = toFile("data_with_header_different_length.csv");
        CsvCounterJacksonImpl csvCounterJacksonImpl = new CsvCounterJacksonImpl(file, mapper);
        ProgramOutput programOutput = csvCounterJacksonImpl.printInfoFromCsv();

        assertEquals(1, programOutput.getTotalCountUsers());
        assertEquals(1, programOutput.getTotalSpend());
        assertEquals(1, programOutput.getNumberUsersWithStandardResolution());
        assertEquals("fa409818ec81dc570fd63f5f776be2c3", programOutput.getFirstUserId());
    }

    @Test
    public void printInfoFromCsv_linesWithDoubleQuotas() throws SwrveException {
        File file = toFile("data_with_header_double_quotas.csv");
        CsvCounterJacksonImpl csvCounterJacksonImpl = new CsvCounterJacksonImpl(file, mapper);
        ProgramOutput programOutput = csvCounterJacksonImpl.printInfoFromCsv();

        assertEquals(3, programOutput.getTotalCountUsers());
        assertEquals(3, programOutput.getTotalSpend());
        assertEquals(2, programOutput.getNumberUsersWithStandardResolution());
        assertEquals("da27c392de83933dbde844d580e9fa60", programOutput.getFirstUserId());
    }

    @Test
    public void printInfoFromCsv_emptyField() throws SwrveException {
        File file = toFile("data_with_header_empty_field.csv");
        CsvCounterJacksonImpl csvCounterJacksonImpl = new CsvCounterJacksonImpl(file, mapper);
        ProgramOutput programOutput = csvCounterJacksonImpl.printInfoFromCsv();

        assertEquals(3, programOutput.getTotalCountUsers());
        assertEquals(2, programOutput.getTotalSpend());
        assertEquals(1, programOutput.getNumberUsersWithStandardResolution());
        assertEquals("da27c392de83933dbde844d580e9fa60", programOutput.getFirstUserId());
    }

    @Test
    public void printInfoFromCsv_emptyLines() throws SwrveException {
        File file = toFile("data_with_header_empty_lines.csv");
        CsvCounterJacksonImpl csvCounterJacksonImpl = new CsvCounterJacksonImpl(file, mapper);
        ProgramOutput programOutput = csvCounterJacksonImpl.printInfoFromCsv();

        assertEquals(3, programOutput.getTotalCountUsers());
        assertEquals(3, programOutput.getTotalSpend());
        assertEquals(2, programOutput.getNumberUsersWithStandardResolution());
        assertEquals("da27c392de83933dbde844d580e9fa60", programOutput.getFirstUserId());
    }

    @Test
    public void printInfoFromCsv_withWhitespacesInsideField() throws SwrveException {
        File file = toFile("data_with_header_whitespaces.csv");
        CsvCounterJacksonImpl csvCounterJacksonImpl = new CsvCounterJacksonImpl(file, mapper);
        ProgramOutput programOutput = csvCounterJacksonImpl.printInfoFromCsv();

        assertEquals(3, programOutput.getTotalCountUsers());
        assertEquals(12, programOutput.getTotalSpend());
        assertEquals(2, programOutput.getNumberUsersWithStandardResolution());
        assertEquals("da27c392de83933dbd e844d580e9fa60 ", programOutput.getFirstUserId());
    }

    @Test
    public void printInfoFromCsv_withoutHeader() throws SwrveException {
        File file = toFile("data_without_header.csv");
        CsvCounterJacksonImpl csvCounterJacksonImpl = new CsvCounterJacksonImpl(file, mapper);
        ProgramOutput programOutput = csvCounterJacksonImpl.printInfoFromCsv();

        assertEquals(3, programOutput.getTotalCountUsers());
        assertEquals(3, programOutput.getTotalSpend());
        assertEquals(2, programOutput.getNumberUsersWithStandardResolution());
        assertEquals("da27c392de83933dbde844d580e9fa60", programOutput.getFirstUserId());
    }

    @Test
    public void printInfoFromCsv_withSpecialCharacter() throws SwrveException {
        File file = toFile("data_with_header_with_special_character.csv");
        CsvCounterJacksonImpl csvCounterJacksonImpl = new CsvCounterJacksonImpl(file, mapper);
        ProgramOutput programOutput = csvCounterJacksonImpl.printInfoFromCsv();

        assertEquals(1, programOutput.getTotalCountUsers());
        assertEquals(1, programOutput.getTotalSpend());
        assertEquals(1, programOutput.getNumberUsersWithStandardResolution());
        assertEquals("da27c392de83933,dbde\"844d580e9fa60", programOutput.getFirstUserId());
    }

    @Test
    public void printInfoFromCsv_emptyFileNoHeader() throws SwrveException {
        File file = toFile("empty_file_no_header.csv");
        CsvCounterJacksonImpl csvCounterJacksonImpl = new CsvCounterJacksonImpl(file, mapper);
        ProgramOutput programOutput = csvCounterJacksonImpl.printInfoFromCsv();

        assertEquals(0, programOutput.getTotalCountUsers());
        assertEquals(0, programOutput.getTotalSpend());
        assertEquals(0, programOutput.getNumberUsersWithStandardResolution());
        assertEquals(null, programOutput.getFirstUserId());
    }

    @Test
    public void printInfoFromCsv_emptyFileWithHeader() throws SwrveException {
        File file = toFile("empty_file_with_header.csv");
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
        File file = toFile("no_exist_file.csv");
        CsvCounterJacksonImpl csvCounterJacksonImpl = new CsvCounterJacksonImpl(file, mapper);
        ProgramOutput programOutput = csvCounterJacksonImpl.printInfoFromCsv();
    }

    @Ignore
    @Test(expected = SwrveException.class)
    public void printInfoFromCsv_nullFileName() throws SwrveException {
        CsvCounterJacksonImpl csvCounterJacksonImpl = new CsvCounterJacksonImpl(null, mapper);
        ProgramOutput programOutput = csvCounterJacksonImpl.printInfoFromCsv();
    }

    private File toFile(String fileName) {
        File file = null;

        try {
            Path path = Paths.get(Objects.requireNonNull(classloader.getResource(fileName)).toURI());
            file = path.toFile();
        } catch (URISyntaxException e) {
            LOGGER.error("Error checking file name {}", fileName);
        }

        return file;
    }
}
