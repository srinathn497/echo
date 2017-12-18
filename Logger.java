package Annotations;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class Logger {

	//@Autowired
	
	private ConsoleWriter consolewriter;
	//@Autowired
	private FileWriter fileWriter;
    
	
	/*@Autowired
	public Logger(ConsoleWriter consoleWriter, FileWriter fileWriter) {
		super();
		this.consoleWriter = consoleWriter;
		this.fileWriter = fileWriter;
	}*/

	@Autowired
	@Qualifier("cwq")
	//@Resource(name="cw2")
	public void setConsoleWriter(ConsoleWriter writer) {
		this.consolewriter = writer;
	}
	
	//@Autowired
    @Inject
    @Named(value="fw2")
	public void setFileWriter(FileWriter fileWriter) {
		this.fileWriter = fileWriter;
	}
	
	public void writeFile(String text) {
		fileWriter.write(text);
	}
	
	public void writeConsole(String text) {
		consolewriter.write(text);
	}

	

}

