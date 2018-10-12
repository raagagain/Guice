package Learn.Guice;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.name.Named;
import com.google.inject.name.Names;

public class ConstantBindings {
	public static void main(String[] args) {
		Injector injector=Guice.createInjector(new TextEditorModuleConstant());
		TextEditorConstant tx=injector.getInstance(TextEditorConstant.class);
		tx.spellcheck();
	}
}

class TextEditorConstant {
	private String dburl;
	
	@Inject
	public TextEditorConstant(@Named("jdbc") String dburl) {
		this.dburl=dburl;
	}
	
	public void spellcheck() {
		System.out.println(dburl);
	}
}

class TextEditorModuleConstant extends AbstractModule {
	@Override
	protected void configure() {
		bind(String.class).annotatedWith(Names.named("jdbc")).toInstance("jdbc:mysql://localhost:5326/db");
	}
}