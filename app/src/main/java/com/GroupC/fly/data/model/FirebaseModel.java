package com.GroupC.fly.data.model;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.GroupC.fly.ActivityLogic.values;
import com.GroupC.fly.data.Objects.Address;
import com.GroupC.fly.data.Objects.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class FirebaseModel {
    // -----------------------------------------------------
    /** DATA MEMBERS **/
    private final FirebaseFirestore db;
    private final Context activityContext;  // Context to the current activity that is using this class object.

    // -----------------------------------------------------
    /** METHODS **/
    public FirebaseModel(Context currContext){
        db = FirebaseFirestore.getInstance();
        activityContext = currContext;

        // Disable Firebase's local data base.
        FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder().setPersistenceEnabled(false).build();
        db.setFirestoreSettings(settings);
    }

    /** User Methods **/

    /**
     *  Inserts a new user into the cloud database.
     */
    public void insertUserToDB(User newUser){
        // Create a new user
        Map<String, Object> user = new HashMap<>();
        user.put(values.KEY_EMAIL, newUser.getEmail());
        user.put(values.KEY_USER_NAME, newUser.getUsername());
        user.put(values.KEY_FIRST_NAME, newUser.getFirstName());
        user.put(values.KEY_LAST_NAME, newUser.getLastName());
        user.put(values.KEY_ADDRESS, newUser.getAddress());
        user.put(values.KEY_JOB, newUser.getJob());
        user.put(values.KEY_AGE, newUser.getAge());
        user.put(values.KEY_ALMA_MATTER, newUser.getAlmaMatter());
        user.put(values.KEY_DOB, newUser.getDateOfBirth());
        user.put(values.KEY_RELATIONSHIP_STATUS, newUser.getRelationshipStatusString());

        // Add a new document with a generated ID
        // Log successful insertion of user to DB.
        // Log unsuccessful insertion of user to DB.
        db.collection(values.COLLECTION_PATH).document(newUser.getEmail())
                .set(user)
                .addOnSuccessListener(aVoid -> { // TODO: design Toast's UI for successful or unsuccessful addition of user.
                    Toast.makeText(activityContext, "Successfully Signed Up", Toast.LENGTH_SHORT).show();
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(activityContext, "Error signing up", Toast.LENGTH_SHORT).show();
                });
    }


    // TODO: complete get all users.
    public void getAllUsersFromDB(){
        db.collection(values.COLLECTION_PATH)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        Vector<User> users = new Vector<>();
                        if (task.isSuccessful()) {
                            QuerySnapshot personsDB = task.getResult();
                            for (QueryDocumentSnapshot personDOC : personsDB) {
                                String name = personDOC.getString("name");
                                String username = personDOC.getString("username");
                                //Address address = personDOC.getString("");
                                String email = personDOC.getString("email");
                                String job = personDOC.getString("job");
                                String almaMatter = personDOC.getString("alma matter");
                                //int age = personDOC.getString("age"); needs to convert string to int.
                                //Person person = new Person(email,name,username,job,almaMatter,0); // handle age, address.
                                //users.add(person);
                            }
                        } else {
                            Log.d("TAG", "Error getting documents: ", task.getException());
                        }
                    }
                });
    }

    /**
     *  Gets and returns a user from the cloud database, by the email provided.
     */
    public Task<DocumentSnapshot> getUserFromDB(String email) {  // TODO: complete get user from database.
        return db.collection(values.COLLECTION_PATH).document(email).get();
    }

    /** Post Methods **/
    // Write methods for writing and receiving posts from database here...

}
