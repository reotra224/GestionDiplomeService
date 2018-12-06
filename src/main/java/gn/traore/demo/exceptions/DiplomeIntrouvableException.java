package gn.traore.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class DiplomeIntrouvableException extends RuntimeException {

	public DiplomeIntrouvableException(String s) {
		super(s);
	}	

}
