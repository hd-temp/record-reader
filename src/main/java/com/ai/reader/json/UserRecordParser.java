package com.ai.reader.json;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.ai.reader.common.UserRecord;
import com.ai.reader.common.model.ReaderConstants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

/**
 * Parses user records from a given file and gives it out in batches
 */
public class UserRecordParser {

	private final JsonReader reader;
	private final Gson gson;
	private final int batchSize; // size of each batch processed

	/**
	 * Parses the file given, with default batch size
	 *
	 * @param fileReader
	 * @throws IOException
	 */
	public UserRecordParser(FileReader fileReader) throws IOException {
		this(ReaderConstants.BATCH_SIZE_DEFAULT, fileReader);
	}

	/**
	 * Parses the file given, in batches of given size
	 *
	 * @param batchSize
	 * @param fileReader
	 * @throws IOException
	 */
	public UserRecordParser(int batchSize, FileReader fileReader) throws IOException {
		if (batchSize <= 0)
			throw new IllegalArgumentException("Invalid batch size");

		if (fileReader == null)
			throw new IllegalArgumentException("Null input");

		this.batchSize = batchSize;

		this.gson = new GsonBuilder().create();

		this.reader = new JsonReader(fileReader);
		this.reader.beginArray(); // prepare to read records in streaming fashion
	}

	/**
	 *
	 * @return True if there are more records to read
	 * @throws IOException
	 */
	public boolean hasNext() throws IOException {
		return reader.hasNext();
	}

	/**
	 *
	 * @return Next batch of user records, limited to batch size
	 * @throws IOException
	 */
	public List<UserRecord> nextBatch() throws IOException {
		if (!hasNext())
			throw new IllegalArgumentException("End of record file");

		List<UserRecord> records = new ArrayList<>();
		do {
			UserRecord record = gson.fromJson(reader, UserRecord.class);
			records.add(record);
		} while (reader.hasNext() && records.size() < batchSize);

		return records;
	}
}
