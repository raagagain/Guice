package Learn.Guice;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class MainGuice {
	public static void main(String... args) {
		Injector injector=Guice.createInjector(new TextEditorModule());
		TextEditor tx=injector.getInstance(TextEditor.class);
		tx.checkspell();
	}
}
