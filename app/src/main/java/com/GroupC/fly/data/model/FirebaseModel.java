package com.GroupC.fly.data.model;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.GroupC.fly.data.Objects.Address;
import com.GroupC.fly.data.Objects.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
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
    private FirebaseFirestore db;

    // -----------------------------------------------------
    /** DEFINED STRINGS **/
    private static final String COLLECTION_PATH = "Users";
    private static final String LOG_TAG = "InsertUserToDB";
    private static final String KEY_FIRST_NAME = "firstName";
    private static final String KEY_LAST_NAME = "lastName";
    private static final String KEY_USER_NAME = "username";
    private static final String KEY_ADDRESS = "address";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_JOB = "kob";
    private static final String KEY_ALMA_MATTER = "alma-matter";
    private static final String KEY_AGE = "age";



    // -----------------------------------------------------
    /** METHODS **/
    public FirebaseModel(){
        db = FirebaseFirestore.getInstance();

        //Disable Firebase's local data base.
        FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder().setPersistenceEnabled(false).build();
        db.setFirestoreSettings(settings);
    }
    public void insertUserToDB(String firstName, String lastName, String username, Address address, String email, String job, String almaMatter, int age){
        // Create a new user
        Map<String, Object> user = new HashMap<>();
        user.put(KEY_EMAIL, email);
        user.put(KEY_USER_NAME, username);
        user.put(KEY_FIRST_NAME, firstName);
        user.put(KEY_LAST_NAME, lastName);
        user.put(KEY_ADDRESS, address);
        user.put(KEY_JOB, job);
        user.put(KEY_AGE, age);
        user.put(KEY_ALMA_MATTER, almaMatter);


        // Add a new document with a generated ID
        db.collection(COLLECTION_PATH).document(email)
                .set(user)
                .addOnSuccessListener(new OnSuccessListener<Void>() { // Log successful insertion of user to DB.
                    @Override
                    public void onSuccess(Void aVoid) { // TODO: Toast.makeText for successful or unsuccessful addition of user.
                        Log.d(LOG_TAG, "Successfully added user");
                    }
                })
                .addOnFailureListener(new OnFailureListener() { // Log unsuccessful insertion of user to DB.
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(LOG_TAG, "Error adding DocumentSnapshot", e);
                    }
                });

    }

    public void getAllUsersFromDB(){
        db.collection(COLLECTION_PATH)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        Vector<User> personsVector = new Vector<>();
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
                                //personsVector.add(person);
                            }
                        } else {
                            Log.d("TAG", "Error getting documents: ", task.getException());
                        }
                    }
                });



    }
}
