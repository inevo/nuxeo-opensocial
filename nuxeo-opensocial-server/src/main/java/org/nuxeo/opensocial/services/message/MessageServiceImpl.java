/**
 * 
 */

package org.nuxeo.opensocial.services.message;

import java.util.List;
import java.util.Set;
import java.util.concurrent.Future;

import org.apache.shindig.auth.SecurityToken;
import org.apache.shindig.protocol.ProtocolException;
import org.apache.shindig.protocol.RestfulCollection;
import org.apache.shindig.social.opensocial.model.Message;
import org.apache.shindig.social.opensocial.model.MessageCollection;
import org.apache.shindig.social.opensocial.spi.CollectionOptions;
import org.apache.shindig.social.opensocial.spi.MessageService;
import org.apache.shindig.social.opensocial.spi.UserId;
import org.nuxeo.runtime.model.ComponentContext;
import org.nuxeo.runtime.model.DefaultComponent;
import org.osgi.framework.Bundle;


/**
 * @author nfgs
 */
public class MessageServiceImpl extends DefaultComponent implements MessageService {

    protected Bundle bundle;
    
    public Bundle getBundle() {
        return bundle;
    }
    
    /**
     * Component activated notification. 
     * Called when the component is activated. All component dependencies are resolved at that moment.
     * Use this method to initialize the component. 
     * <p>
     * The default implementation of this method is storing the Bundle owning that component in a class field.
     * You can use the bundle object to lookup for bundle resources:
     * <code>URL url = bundle.getEntry("META-INF/some.resource");</code>, load classes or to interact with OSGi framework.
     * <p> 
     * Note that you must always use the Bundle to lookup for resources in the bundle. Do not use the classloader for this.
     * @param context the component context. Use it to get the current bundle context
     */
    @Override
    public void activate(ComponentContext context) {
        this.bundle = context.getRuntimeContext().getBundle(); 
    }

    /**
     * Component deactivated notification.
     * Called before a component is unregistered.
     * Use this method to do cleanup if any and free any resources held by the component.
     *  
     * @param context the component context. Use it to get the current bundle context
     */
    @Override
    public void deactivate(ComponentContext context) {
        this.bundle = null;
    }

    /**
     * Application started notification.
     * Called after the application started. 
     * You can do here any initialization that requires a working application 
     * (all resolved bundles and components are active at that moment) 
     * 
     * @param context the component context. Use it to get the current bundle context
     * @throws Exception
     */
    @Override
    public void applicationStarted(ComponentContext context) throws Exception {
        // do nothing by default. You can remove this method if not used.
    }

	@Override
	public Future<Void> createMessage(UserId arg0, String arg1, String arg2,
			Message arg3, SecurityToken arg4) throws ProtocolException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Future<MessageCollection> createMessageCollection(UserId arg0,
			MessageCollection arg1, SecurityToken arg2)
			throws ProtocolException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Future<Void> deleteMessageCollection(UserId arg0, String arg1,
			SecurityToken arg2) throws ProtocolException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Future<Void> deleteMessages(UserId arg0, String arg1,
			List<String> arg2, SecurityToken arg3) throws ProtocolException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Future<RestfulCollection<MessageCollection>> getMessageCollections(
			UserId arg0, Set<String> arg1, CollectionOptions arg2,
			SecurityToken arg3) throws ProtocolException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Future<RestfulCollection<Message>> getMessages(UserId arg0,
			String arg1, Set<String> arg2, List<String> arg3,
			CollectionOptions arg4, SecurityToken arg5)
			throws ProtocolException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Future<Void> modifyMessage(UserId arg0, String arg1, String arg2,
			Message arg3, SecurityToken arg4) throws ProtocolException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Future<Void> modifyMessageCollection(UserId arg0,
			MessageCollection arg1, SecurityToken arg2)
			throws ProtocolException {
		// TODO Auto-generated method stub
		return null;
	}

}