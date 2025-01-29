package com.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InOrder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

class BookingSystemTest {

    private BookingSystem bookingSystem;
    private TimeProvider timeProvider;
    private RoomRepository roomRepository;
    private NotificationService notificationService;
    private Room room;

    @BeforeEach
    void setUp() {
        timeProvider = mock(TimeProvider.class);
        roomRepository = mock(RoomRepository.class);
        notificationService = mock(NotificationService.class);
        bookingSystem = new BookingSystem(timeProvider, roomRepository, notificationService);

        room = mock(Room.class);
    }


    @Test
    @DisplayName("Booking fails when the room is occupied")
    void testBookingFailsWhenRoomUnavailable() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime startTime = now.plusHours(1);
        LocalDateTime endTime = now.plusHours(2);
        String roomId = "room-1";

        when(timeProvider.getCurrentTime()).thenReturn(now);
        when(roomRepository.findById(roomId)).thenReturn(Optional.of(room));
        when(room.isAvailable(startTime, endTime)).thenReturn(false);

        boolean result = bookingSystem.bookRoom(roomId, startTime, endTime);

        assertThat(result).isFalse();
        verifyNoInteractions(notificationService);
    }

    @ParameterizedTest
    @CsvSource({
            "2023-01-01T12:00, 2023-01-01T13:00, true",
            "2023-01-01T12:00, 2023-01-01T11:00, false",
            "2023-01-02T12:00, 2023-01-01T12:00, false"
    })
    @DisplayName("Validates end time and start time")
    void testBookingTimeValidation(String start, String end, boolean isValid) {
        LocalDateTime startTime = LocalDateTime.parse(start);
        LocalDateTime endTime = LocalDateTime.parse(end);
        String roomId = "room-1";

        when(timeProvider.getCurrentTime()).thenReturn(LocalDateTime.parse("2022-12-31T10:00"));
        when(roomRepository.findById(roomId)).thenReturn(Optional.of(room));
        when(room.isAvailable(startTime, endTime)).thenReturn(true);

        if (!isValid) {
            assertThatThrownBy(() -> bookingSystem.bookRoom(roomId, startTime, endTime))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("End time must be after the start time");
        } else {
            assertThat(bookingSystem.bookRoom(roomId, startTime, endTime)).isTrue();
        }


    }








}

