package com.rungroup.backend.controller;

import com.rungroup.backend.exception.BookingNotFoundException;
import com.rungroup.backend.model.Booking;
import com.rungroup.backend.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
public class BookingController {

    @Autowired
    private BookingRepository bookingRepository;

    @PostMapping("/booking")
    Booking newUser(@RequestBody Booking newBooking) {
        return bookingRepository.save(newBooking);
    }

    @GetMapping("/booking")
    List<Booking> getAllBooking() {
        return bookingRepository.findAll();
    }

    @GetMapping("/booking/{id}")
    Booking getBookingById(@PathVariable Long id) {
        return bookingRepository.findById(id)
                .orElseThrow(() -> new BookingNotFoundException(id));
    }

    @PutMapping("/booking/{id}")
    Booking updateBooking(@RequestBody Booking newBooking, @PathVariable Long id) {
        return bookingRepository.findById(id)
                .map(booking -> {
                    booking.setPassengerName(newBooking.getPassengerName());
                    booking.setNik(newBooking.getNik());
                    booking.setAge(newBooking.getAge());
                    return bookingRepository.save(booking);
                }).orElseThrow(() -> new BookingNotFoundException(id));
    }

    @DeleteMapping("/booking/{id}")
    String deleteBooking(@PathVariable Long id){
        if(!bookingRepository.existsById(id)){
            throw new BookingNotFoundException(id);
        }
        bookingRepository.deleteById(id);
        return  "Booking with id "+id+" has been deleted.";
    }
}
