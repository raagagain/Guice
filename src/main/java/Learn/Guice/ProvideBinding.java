package Learn.Guice;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Provides;
import com.google.inject.name.Named;
import com.google.inject.name.Names;

public class ProvideBinding {

	public static void main(String[] args) {
		Injector injector=Guice.createInjector(new TextEditorModuleProvides());
		TextEditorProvides tx=injector.getInstance(TextEditorProvides.class);
		tx.makespellcheck();
	}
}

class TextEditorProvides {
	private SpellCheckProvides spc;
	
	@Inject 
	public TextEditorProvides(SpellCheckProvides spc) {
		this.spc=spc;
	}
	
	public void makespellcheck() {
		spc.checkspelling();
	}
}

class TextEditorModuleProvides extends AbstractModule {
	@Override
	protected void configure() {
		bind(String.class).annotatedWith(Names.named("name")).toInstance("Rahul");
	}
	
	@Provides
	public SpellCheckProvides providespellcheck() {
		String url="jdbc:mysql://localhost:5326/db";
		String user="root";
		Integer time=25;
		
		SpellCheckProvides scp=new SpellCheckProvidesImpl(url,user,time);
		return scp;
	}
}

interface SpellCheckProvides{
	void checkspelling();
}

class SpellCheckProvidesImpl implements SpellCheckProvides{
	private String url;
	private String user;
	private Integer timeout;
	@Inject @Named("name") 
	private String name;
	
	
	public SpellCheckProvidesImpl() {}
	
	@Inject
	public SpellCheckProvidesImpl(String url,String user,Integer timeout) {
		this.url=url;
		this.user=user;
		this.timeout=timeout;
	}
	public void checkspelling() {
		System.out.println("SpellChecker");
		System.out.println(url);
		System.out.println(user);
		System.out.println(timeout);
		System.out.println(name);

	}
}
