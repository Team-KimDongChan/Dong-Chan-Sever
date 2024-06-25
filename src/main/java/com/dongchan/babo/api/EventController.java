package com.dongchan.babo.api;

import com.dongchan.babo.api.req.EventReq;
import com.dongchan.babo.api.req.EventWithIdReq;
import com.dongchan.babo.common.Response;
import com.dongchan.babo.common.ResponseData;
import com.dongchan.babo.service.EventService;
import com.dongchan.babo.service.res.EventRes;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/event")
public class EventController {
    private final EventService eventService;

    @PostMapping
    public Response register(@RequestBody @Valid EventReq req){
        return eventService.register(req);
    }

    @GetMapping
    public ResponseData<List<EventRes>> get(){
        return eventService.getList();
    }

    @PatchMapping
    public Response modify(@RequestBody @Valid EventWithIdReq req){
        return eventService.modify(req);
    }

    @DeleteMapping
    public Response delete(@RequestParam Long id){
        return eventService.delete(id);
    }
}
