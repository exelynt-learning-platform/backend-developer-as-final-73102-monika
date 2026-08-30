package com.monika.booking.service;

import com.monika.booking.entity.Reservation;
import com.monika.booking.entity.ReservationStatus;
import com.monika.booking.repository.ReservationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;

    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    public Optional<Reservation> getReservationById(Long id) {
        return reservationRepository.findById(id);
    }

    public Reservation createReservation(Reservation reservation) {
        reservation.setStatus(ReservationStatus.PENDING);
        return reservationRepository.save(reservation);
    }

    public Reservation updateReservation(Long id, Reservation reservation) {
        Optional<Reservation> existingReservation =
                reservationRepository.findById(id);

        if (existingReservation.isPresent()) {
            Reservation existing = existingReservation.get();

            existing.setStartTime(reservation.getStartTime());
            existing.setEndTime(reservation.getEndTime());
            existing.setStatus(reservation.getStatus());

            return reservationRepository.save(existing);
        }

        return null;
    }

    public void deleteReservation(Long id) {
        reservationRepository.deleteById(id);
    }
}
