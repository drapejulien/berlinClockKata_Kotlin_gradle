package fr.jdrape.kata.berlinclockkata.controller


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import fr.jdrape.kata.berlinclockkata.service.BerlinClockService;

@RestController
@RequestMapping("/clockConverter")
class ClockController () {
	
   @Autowired
   lateinit var service:BerlinClockService;

    /**
     * Convert digital time HH:mm:ss to Berlin Time.
     * 
     * @param time
     *            : time to convert
     * @return Berlin Time
     */
    @GetMapping("digitalToBerlin")
    fun digitalToBerlin(
            @RequestParam(value = "time", required = true) time:String) : String {
        return service.convertDigitalToBerlin(time);
    }
	
}