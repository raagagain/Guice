package Learn.Guice;

import java.util.logging.Logger;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Provider;

public class ProviderBinding {
	public static void main(String []args) {
		Injector injector=Guice.createInjector(new TextEditorModuleProvider());
		TextEditorProvider tx=injector.getInstance(TextEditorProvider.class);
		tx.doCall();
	}
}

class TextEditorProvider {
	private SpellCheckerProvider spc;
	private Logger logger;
	
	@Inject 
	public TextEditorProvider(SpellCheckerProvider spc,Logger logger) {
		this.spc=spc;
		this.logger=logger;
	}
	
	public void doCall() {
		System.out.println("Inside Call");
		spc.checkspelling();
		logger.info("Raag Is It");
		
	}
}

class TextEditorModuleProvider extends AbstractModule
{
	@Override
	protected void configure() {
		bind(SpellCheckerProvider.class).toProvider(SpellCheckProvider.class);
	}
}

interface SpellCheckerProvider{
	void checkspelling();
}

class SpellCheckerProviderImpl implements SpellCheckerProvider{
	private String dburl;
	private String user;
	private Integer t;
	
	@Inject
	public SpellCheckerProviderImpl(String dburl,String user,Integer t) {
		this.dburl=dburl;
		this.user=user;
		this.t=t;
	}
	
	public void checkspelling() {
		System.out.println("");
		System.out.println(dburl);
		System.out.println(user);
		System.out.println(t);
	}
}

class SpellCheckProvider implements Provider<SpellCheckerProvider>{
//	@Override
	public SpellCheckerProvider get() {
		String url="jdbc:mysql://localhost:5326/db";
		String user="root";
		Integer time=25;
		
		SpellCheckerProvider SpellCheckerProvider=new SpellCheckerProviderImpl(url,user,time);
		return SpellCheckerProvider;
	}
}

