package Learn.Guice;

import com.google.inject.Inject;

public class TextEditor {
	private SpellCheck spl;
	
	/*@Inject
	public TextEditor(SpellCheck sp) {
		this.spl=sp;
	}*/
	
	@Inject
	public TextEditor(@WinWord SpellCheck sp) {
		this.spl=sp;
	}
	
	/*@Inject
	public TextEditor(@Named("WinWord") SpellCheck sp) {
		this.spl=sp;
	}*/
	
	public void checkspell() {
		System.out.println("Hi");
		spl.checkspelling();
	}
	
}
