package ch.vorburger.meta.emf.firebase;

import org.junit.Test;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.ValueEventListener;

public class FirebaseTest {

	@Test
	public void testFirebase() {
		String url = "";
		Firebase firebase = new Firebase(url);
		ChildEventListener childEventlistener = new ChildEventListener() {
			
			@Override
			public void onChildRemoved(DataSnapshot arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onChildMoved(DataSnapshot arg0, String arg1) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onChildChanged(DataSnapshot arg0, String arg1) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onChildAdded(DataSnapshot arg0, String arg1) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onCancelled() {
			}
		};
		firebase.addChildEventListener(childEventlistener);
		ValueEventListener valueEventListener = new ValueEventListener() {
			
			@Override
			public void onDataChange(DataSnapshot arg0) {
			}
			
			@Override
			public void onCancelled() {
			}
		};
		firebase.addValueEventListener(valueEventListener);
	}
}
