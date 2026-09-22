package com.ariv.problemsolving.patterns.prefixsum;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("MeetingRooms")
class MeetingRoomsTest {

    @Nested
    @DisplayName("canAttendAllMeetings")
    class CanAttendAllMeetingsTests {

        @Test
        @DisplayName("should return false when meetings overlap")
        void shouldReturnFalseWhenMeetingsOverlap() {

            assertFalse(
                    MeetingRooms.canAttendAllMeetings(
                            new int[][]{
                                    {0, 30},
                                    {5, 10},
                                    {15, 20}
                            }
                    )
            );
        }

        @Test
        @DisplayName("should return true when meetings do not overlap")
        void shouldReturnTrueWhenMeetingsDoNotOverlap() {

            assertTrue(
                    MeetingRooms.canAttendAllMeetings(
                            new int[][]{
                                    {7, 10},
                                    {12, 14}
                            }
                    )
            );
        }

        @Test
        @DisplayName("should handle empty input")
        void shouldHandleEmptyInput() {

            assertTrue(
                    MeetingRooms.canAttendAllMeetings(
                            new int[][]{}
                    )
            );
        }

        @Test
        @DisplayName("should handle single meeting")
        void shouldHandleSingleMeeting() {

            assertTrue(
                    MeetingRooms.canAttendAllMeetings(
                            new int[][]{
                                    {1, 5}
                            }
                    )
            );
        }

        @Test
        @DisplayName("should handle meetings that touch boundaries")
        void shouldHandleMeetingsThatTouchBoundaries() {

            assertTrue(
                    MeetingRooms.canAttendAllMeetings(
                            new int[][]{
                                    {1, 5},
                                    {5, 10}
                            }
                    )
            );
        }

        @Test
        @DisplayName("should work with unsorted input")
        void shouldWorkWithUnsortedInput() {

            assertFalse(
                    MeetingRooms.canAttendAllMeetings(
                            new int[][]{
                                    {15, 20},
                                    {0, 30},
                                    {5, 10}
                            }
                    )
            );
        }

        @Test
        @DisplayName("should reject null input")
        void shouldRejectNullInput() {

            assertThrows(
                    NullPointerException.class,
                    () -> MeetingRooms.canAttendAllMeetings(
                            null
                    )
            );
        }
    }
}