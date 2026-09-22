package com.ariv.problemsolving.patterns.prefixsum;

import java.util.Arrays;
import java.util.Objects;

public final class MeetingRooms {

    private MeetingRooms() {
    }

    public static boolean canAttendAllMeetings(
            int[][] meetings) {

        Objects.requireNonNull(
                meetings,
                "Meetings cannot be null"
        );

        if (meetings.length <= 1) {
            return true;
        }

        int[][] sortedMeetings =
                Arrays.copyOf(
                        meetings,
                        meetings.length
                );

        Arrays.sort(
                sortedMeetings,
                (left, right) ->
                        Integer.compare(
                                left[0],
                                right[0]
                        )
        );

        for (int index = 1;
             index < sortedMeetings.length;
             index++) {

            int previousEnd =
                    sortedMeetings[index - 1][1];

            int currentStart =
                    sortedMeetings[index][0];

            if (currentStart < previousEnd) {
                return false;
            }
        }

        return true;
    }
}
