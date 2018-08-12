package com.ai.reader.json;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.junit.Test;

import com.ai.reader.common.UserRecord;

public class UserRecordParserTest {

	public static final String TEST_FILE_PATH = "src/test/resources/users.json"; // small test file of 4 records

	@Test(expected = IllegalArgumentException.class)
	public void invalidBatchSize() throws IOException {
		new UserRecordParser(0, new FileReader(TEST_FILE_PATH));
	}

	@Test(expected = IOException.class)
	public void invalidFile() throws IOException {
		new UserRecordParser(1, new FileReader("invalid_file"));
	}

	@Test
	public void testSmallBatch() throws IOException {
		UserRecordParser parser = new UserRecordParser(2, new FileReader(TEST_FILE_PATH));

		List<UserRecord> batch1 = parser.nextBatch();
		assertNotNullSize(2, batch1);

		List<UserRecord> batch2 = parser.nextBatch();
		assertNotNullSize(2, batch2);

		assertFalse(parser.hasNext());
	}

	@Test
	public void testLastBatchSmaller() throws IOException {
		UserRecordParser parser = new UserRecordParser(3, new FileReader(TEST_FILE_PATH));

		List<UserRecord> batch1 = parser.nextBatch();
		assertNotNullSize(3, batch1);

		List<UserRecord> batch2 = parser.nextBatch();
		assertNotNullSize(1, batch2);

		assertFalse(parser.hasNext());
	}

	@Test
	public void testDefaultBatchSize() throws IOException {
		UserRecordParser parser = new UserRecordParser(new FileReader(TEST_FILE_PATH));

		List<UserRecord> batch1 = parser.nextBatch();
		assertNotNullSize(4, batch1);

		assertFalse(parser.hasNext());
	}

	private void assertNotNullSize(int expectedSize, List<UserRecord> batch) {
		assertEquals(expectedSize, batch.size());
		batch.forEach(record -> assertNotNull(record));
	}
}
