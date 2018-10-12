package Learn.Guice;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import com.google.inject.AbstractModule;
import com.google.inject.BindingAnnotation;
import com.google.inject.name.Names;

@BindingAnnotation @Target({ FIELD, PARAMETER, METHOD }) @Retention(RUNTIME)
@interface WinWord {}
@BindingAnnotation @Target({ FIELD, PARAMETER, METHOD }) @Retention(RUNTIME)
@interface WinWin {}

public class TextEditorModule extends AbstractModule{
	@Override
	protected void configure() {
		bind(SpellCheck.class).to(SpellCheckImpl.class);
		/*bind(SpellCheckImpl.class).to(WordSpellCheckImpl.class);*/ //Gives Error
		bind(SpellCheck.class).annotatedWith(WinWord.class).to(WordSpellCheckImpl.class);
		//bind(SpellCheck.class).annotatedWith(Names.named("WinWord")).to(WordSpellCheckImpl.class);
		bind(SpellCheck.class).annotatedWith(WinWin.class).to(WinSpellCheckImpl.class);
	}
}
