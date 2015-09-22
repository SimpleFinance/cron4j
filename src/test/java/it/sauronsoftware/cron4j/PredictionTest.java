/**
 * Author: Ian Eure <ieure@simple.com>
 */
package it.sauronsoftware.cron4j;

import org.junit.Test;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import java.util.TimeZone;

import static org.junit.Assert.*;

public class PredictionTest {
  @Test
  public void testPredictionTimezone() throws Exception {
    final TimeZone origTz = TimeZone.getDefault();
    try {
      TimeZone.setDefault(DateTimeZone.forID("America/Los_Angeles").toTimeZone());

      final DateTimeZone tz = DateTimeZone.forID("America/New_York");
      final String schedule = "1 6 * * *";
      final Long start = 1442926801150L;
      final Predictor p = new Predictor(schedule, start);
      p.setTimeZone(tz.toTimeZone());

      final DateTime candidate = new DateTime(p.nextMatchingTime(), tz);
      assertEquals(6, candidate.hourOfDay().get());

    } finally {
      TimeZone.setDefault(origTz);
    }
  }
}
