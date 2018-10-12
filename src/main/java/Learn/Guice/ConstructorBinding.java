package Learn.Guice;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.name.Named;
import com.google.inject.name.Names;

public class ConstructorBinding {
	public static void main(String[] args) {
		Injector injector=Guice.createInjector(new BindModule());
		TextEditorConstructor tx=injector.getInstance(TextEditorConstructor.class);
		tx.checkspelling();
	}

}

class TextEditorConstructor {
	private SpellCheckConstructor spc;
	
	@Inject
	public TextEditorConstructor(SpellCheckConstructor spc) {
		this.spc=spc;
	}
	
	public void checkspelling() {
		spc.checkspell();
	}
}

class BindModule extends AbstractModule {
	protected void configure() {
		bind(String.class).annotatedWith(Names.named("jdbc")).toInstance("jdbc:mysql://localhost:5432/db");
		bind(String.class).annotatedWith(Names.named("name")).toInstance("Rahul");

		try {
			bind(SpellCheckConstructor.class).toConstructor(SpellCheckConstructorImpl.class.getConstructor(String.class));
		}
		catch(Exception e) {
			System.out.println("Created Exception");
		}
	}
}

interface SpellCheckConstructor{
	public void checkspell();
}

class SpellCheckConstructorImpl implements SpellCheckConstructor {
	
	private String url;
	@Inject @Named("name") 
	private String name;
	public SpellCheckConstructorImpl() {}
	
	public SpellCheckConstructorImpl(@Named("jdbc")String url) {
		this.url=url;
	}
	
	
	public void checkspell() {
		System.out.println("Inside CheckSpell");
		System.out.println(url);
		System.out.println(name);
	}
}