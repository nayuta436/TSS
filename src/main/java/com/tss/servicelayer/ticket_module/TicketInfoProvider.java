package com.tss.servicelayer.ticket_module;

import java.util.ArrayList;
import java.util.List;

public class TicketInfoProvider {
    public List<TicketType> getTicketTypes() {
        List<TicketType> ticketTypes = new ArrayList<>();
        ticketTypes.add(new TicketType("单程票", 10.0));
        ticketTypes.add(new TicketType("多次往返票", 50.0));
        return ticketTypes;
    }

    public List<SeatType> getSeatTypes() {
        List<SeatType> seatTypes = new ArrayList<>();
        seatTypes.add(new SeatType("普通座", 0.0));
        seatTypes.add(new SeatType("商务座", 20.0));
        return seatTypes;
    }
}
