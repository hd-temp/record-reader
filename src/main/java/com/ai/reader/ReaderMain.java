package com.ai.reader;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.ai.reader.common.UserRecord;
import com.ai.reader.common.model.ReaderConstants;
import com.ai.reader.json.UserRecordParser;
import com.ai.reader.query.MedianFriends;
import com.ai.reader.query.Query;
import com.ai.reader.query.TotalMessageActiveFemale;
import com.ai.reader.query.BinRegistrationYear;
import com.ai.reader.query.MeanBalance;
import com.ai.reader.query.MedianAge;

public class ReaderMain {

	public static Collection<Query<?, ?>> queries;

	static {
		queries = new ArrayList<>();
		queries.add(new BinRegistrationYear());
		queries.add(new MedianFriends());
		queries.add(new MedianAge());
		queries.add(new MeanBalance());
		queries.add(new TotalMessageActiveFemale());
	}

	public static void main(String[] args) throws IOException {
		if (args.length < 1)
			throw new IllegalArgumentException("json file location not given");

		// Batch size parameter optional
		final int batchSize = args.length == 2 ? Integer.parseInt(args[1]) : ReaderConstants.BATCH_SIZE_DEFAULT;

		UserRecordParser parser = new UserRecordParser(batchSize, new FileReader(args[0]));
		parseBatches(parser);
	}

	private static void parseBatches(UserRecordParser parser) throws IOException {
		int i = 1;
		while (parser.hasNext()) {
			System.out.println("Processed batch " + i + ", stats so far:");
			List<UserRecord> records = parser.nextBatch();
			queryRecords(records);
			i++;
		}
	}

	private static void queryRecords(List<UserRecord> records) {
		queries.forEach(query -> {
			query.processRecords(records);
			System.out.println(query.getResults());
		});
		System.out.println();
	}
}
