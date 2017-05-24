package com.company.decode;

import com.com.snowplowanalytics.snowplow.CollectorPayload.thrift.model1.CollectorPayload;
import com.twitter.elephantbird.mapreduce.io.ThriftBlockReader;
import com.twitter.elephantbird.util.TypeRef;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;

/**
 * User: vshulga
 * Date: 5/24/17 7:19 PM
 */
public class EventDecoder {
    private static final Logger log = LoggerFactory.getLogger(EventDecoder.class);
    private static final String RESOURCE_NAME =
            "/2017-05-23-49573334505487429614427513964717871542635044026141638658" +
                    "-49573334505487429614427513964720289394274273284491051010";

    public static void main(String[] args) throws IOException {
        InputStream is = EventDecoder.class.getResourceAsStream(RESOURCE_NAME);

        ThriftBlockReader<CollectorPayload> thriftReader = new ThriftBlockReader<>(
                is, new TypeRef<CollectorPayload>() {}
        );
        CollectorPayload record;
        int foundRecordsCount = 0;
        while ((record = thriftReader.readNext()) != null) {
            foundRecordsCount++;
            log.info("Collector payload is: {}", record);
        }
        log.info("Found {} records", foundRecordsCount);
    }
}