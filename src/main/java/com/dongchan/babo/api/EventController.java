package com.dongchan.babo.api;

import com.dongchan.babo.api.req.EventReq;
import com.dongchan.babo.api.req.EventWithIdReq;
import com.dongchan.babo.service.EventService;
import com.dongchan.babo.service.res.EventRes;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/event")
public class EventController {
    private final EventService eventService;

    @PostMapping
    public void register(@RequestBody @Valid EventReq req){
        eventService.register(req);
    }

    @GetMapping
    public List<EventRes> get(){
        return eventService.getList();
    }

    @PatchMapping
    public void modify(EventWithIdReq req){
        eventService.modify(req);
    }

    @DeleteMapping
    public void delete(@RequestParam Long id){
        eventService.delete(id);
    }
}
