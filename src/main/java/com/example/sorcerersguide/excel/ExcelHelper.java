package com.example.sorcerersguide.excel;

import com.example.sorcerersguide.model.Allocation;
import com.example.sorcerersguide.model.Faq;
import com.example.sorcerersguide.model.Query;
import com.example.sorcerersguide.model.Update;
import org.apache.commons.csv.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ExcelHelper {

    public static String TYPE = "text/csv";
    static String[] UPDATE_HEADERS = { "id", "heading", "body", "update_date", "added_by" };
    static String[] QUERIES_HEADERS = { "query_id", "query_link", "query_question", "query_requester", "query_status", "resolver_login", "resolver_response", "created_date", "response_date" };
    static String[] FAQ_HEADERS = { "id", "date", "question", "answer", "is_deprecated" };
    static String[] ALLOCATION_HEADERS = { "case_id", "asin", "reviewer_id", "manager_id", "is_completed", "comment" };

    public static boolean hasCSVFormat(MultipartFile file) {
        return TYPE.equals(file.getContentType())
                || Objects.equals(file.getContentType(), "application/vnd.ms-excel");
    }

    public static List<Update> csvToUpdates(InputStream is) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim())) {

            List<Update> updates = new ArrayList<>();

            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord csvRecord : csvRecords) {
                Update update = new Update(
                        csvRecord.get(UPDATE_HEADERS[0]),
                        csvRecord.get(UPDATE_HEADERS[1]),
                        csvRecord.get(UPDATE_HEADERS[2]),
                        csvRecord.get(UPDATE_HEADERS[3]),
                        csvRecord.get(UPDATE_HEADERS[4])
                );

                updates.add(update);
            }

            return updates;
        } catch (IOException e) {
            throw new RuntimeException("Failed to parse CSV file: " + e.getMessage());
        }
    }

    public static List<Query> csvToQueries(InputStream is) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim())) {

            List<Query> queries = new ArrayList<>();

            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord csvRecord : csvRecords) {
                Query query = new Query(
                        Long.parseLong(csvRecord.get(QUERIES_HEADERS[0])),
                        csvRecord.get(QUERIES_HEADERS[1]),
                        csvRecord.get(QUERIES_HEADERS[2]),
                        csvRecord.get(QUERIES_HEADERS[3]),
                        csvRecord.get(QUERIES_HEADERS[4]),
                        csvRecord.get(QUERIES_HEADERS[5]),
                        csvRecord.get(QUERIES_HEADERS[6]),
                        csvRecord.get(QUERIES_HEADERS[7]),
                        csvRecord.get(QUERIES_HEADERS[7])
                );

                queries.add(query);
            }

            return queries;
        } catch (IOException e) {
            throw new RuntimeException("Failed to parse CSV file: " + e.getMessage());
        }
    }

    public static List<Faq> csvToFaqs(InputStream is) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim())) {

            List<Faq> faqs = new ArrayList<>();

            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord csvRecord : csvRecords) {
                Faq update = new Faq(
                        csvRecord.get(FAQ_HEADERS[0]),
                        csvRecord.get(FAQ_HEADERS[1]),
                        csvRecord.get(FAQ_HEADERS[2]),
                        csvRecord.get(FAQ_HEADERS[3]),
                        csvRecord.get(FAQ_HEADERS[4])
                );

                faqs.add(update);
            }

            return faqs;
        } catch (IOException e) {
            throw new RuntimeException("Failed to parse CSV file: " + e.getMessage());
        }
    }

    public static List<Allocation> csvToAllocations(InputStream is) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim())) {

            List<Allocation> allocations = new ArrayList<>();

            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord csvRecord : csvRecords) {
                Allocation allocation = new Allocation(
                        csvRecord.get(ALLOCATION_HEADERS[0]),
                        csvRecord.get(ALLOCATION_HEADERS[1]),
                        csvRecord.get(ALLOCATION_HEADERS[2]),
                        csvRecord.get(ALLOCATION_HEADERS[3]),
                        csvRecord.get(ALLOCATION_HEADERS[4]),
                        csvRecord.get(ALLOCATION_HEADERS[5])
                );

                allocations.add(allocation);
            }

            return allocations;
        } catch (IOException e) {
            throw new RuntimeException("Failed to parse CSV file: " + e.getMessage());
        }
    }

    public static ByteArrayInputStream updatesToCsv(List<Update> updates) {
        final CSVFormat format = CSVFormat.DEFAULT.withQuoteMode(QuoteMode.MINIMAL);

        try (ByteArrayOutputStream out = new ByteArrayOutputStream();
            CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), format)) {

            List<String> header = Arrays.asList(
                    UPDATE_HEADERS[0],
                    UPDATE_HEADERS[1],
                    UPDATE_HEADERS[2],
                    UPDATE_HEADERS[3],
                    UPDATE_HEADERS[4]
            );

            csvPrinter.printRecord(header);

            for (Update update : updates) {
                List<String> data = Arrays.asList(
                        String.valueOf(update.getId()),
                        update.getHeading(),
                        update.getBody(),
                        update.getUpdateDate(),
                        update.getAddedBy()
                );

                csvPrinter.printRecord(data);
            }

            csvPrinter.flush();
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("Failed to export data to CSV file: " + e.getMessage());
        }
    }

    public static ByteArrayInputStream queriesToCsv(List<Query> queries) {
        final CSVFormat format = CSVFormat.DEFAULT.withQuoteMode(QuoteMode.MINIMAL);

        try (ByteArrayOutputStream out = new ByteArrayOutputStream();
            CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), format)) {

            List<String> header = Arrays.asList(
                    QUERIES_HEADERS[0],
                    QUERIES_HEADERS[1],
                    QUERIES_HEADERS[2],
                    QUERIES_HEADERS[3],
                    QUERIES_HEADERS[4],
                    QUERIES_HEADERS[5],
                    QUERIES_HEADERS[6],
                    QUERIES_HEADERS[7],
                    QUERIES_HEADERS[7]
            );

            csvPrinter.printRecord(header);

            for (Query query : queries) {
                List<String> data = Arrays.asList(
                        String.valueOf(query.getQueryId()),
                        query.getQueryLink(),
                        query.getQueryQuestion(),
                        query.getQueryRequester(),
                        query.getQueryStatus(),
                        query.getResolverLogin(),
                        query.getResolverResponse(),
                        query.getCreatedDate(),
                        query.getResponseDate()
                );

                csvPrinter.printRecord(data);
            }

            csvPrinter.flush();
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("Failed to export data to CSV file: " + e.getMessage());
        }
    }

    public static ByteArrayInputStream faqsToCsv(List<Faq> faqs) {
        final CSVFormat format = CSVFormat.DEFAULT.withQuoteMode(QuoteMode.MINIMAL);

        try (ByteArrayOutputStream out = new ByteArrayOutputStream();
            CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), format)) {

            List<String> header = Arrays.asList(
                    FAQ_HEADERS[0],
                    FAQ_HEADERS[1],
                    FAQ_HEADERS[2],
                    FAQ_HEADERS[3],
                    FAQ_HEADERS[4]
            );

            csvPrinter.printRecord(header);

            for (Faq faq : faqs) {
                List<String> data = Arrays.asList(
                        String.valueOf(faq.getId()),
                        faq.getDate(),
                        faq.getQuestion(),
                        faq.getAnswer(),
                        faq.getIsDeprecated()
                );

                csvPrinter.printRecord(data);
            }

            csvPrinter.flush();
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("Failed to export data to CSV file: " + e.getMessage());
        }
    }

    public static ByteArrayInputStream allocationsToCsv(List<Allocation> allocations) {
        final CSVFormat format = CSVFormat.DEFAULT.withQuoteMode(QuoteMode.MINIMAL);

        try (ByteArrayOutputStream out = new ByteArrayOutputStream();
             CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), format)) {

            List<String> header = Arrays.asList(
                    ALLOCATION_HEADERS[0],
                    ALLOCATION_HEADERS[1],
                    ALLOCATION_HEADERS[2],
                    ALLOCATION_HEADERS[3],
                    ALLOCATION_HEADERS[4],
                    ALLOCATION_HEADERS[5]
            );

            csvPrinter.printRecord(header);

            for (Allocation allocation : allocations) {
                List<String> data = Arrays.asList(
                        allocation.getCaseId(),
                        allocation.getAsin(),
                        allocation.getReviewerId(),
                        allocation.getManagerId(),
                        allocation.getIsCompleted(),
                        allocation.getComment()
                );

                csvPrinter.printRecord(data);
            }

            csvPrinter.flush();
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("Failed to export data to CSV file: " + e.getMessage());
        }
    }
}
