package com.GroupC.fly.data.model;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.GroupC.fly.ActivityLogic.MainActivity;
import com.GroupC.fly.ActivityLogic.SignUpActivity;
import com.GroupC.fly.ActivityLogic.SignUpActivity2;
import com.GroupC.fly.data.Objects.Address;
import com.GroupC.fly.data.Objects.Person;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import android.widget.Toast;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class FirebaseModel {
    /** DATA MEMBERS **/
    FirebaseFirestore db;

    /** METHODS **/
    public FirebaseModel(){
        db = FirebaseFirestore.getInstance();

        //Disable Firebase's local data base.
        FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder().setPersistenceEnabled(false).build();
        db.setFirestoreSettings(settings);
    }
    public void insertUserToDB(String name, String username, Address address, String email, String job, String almaMatter, int age){
        // Create a new person
        Map<String, Object> person = new HashMap<>();
        person.put("name", name);
        person.put("username", username);
        person.put("address", address);
        person.put("email", email);
        person.put("job", job);
        person.put("alma matter", almaMatter);
        person.put("age", age);

        // Add a new document with a generated ID
        db.collection("Persons")
                .add(person);
              /*  .addOnCanceledListener(new OnCompleteListener<Void>(){
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        Toast.makeText(SignUpActivity2.this, "User successfuly created", Toast.LENGTH_SHORT).show();
                    }

                });*/

    }

    public void getAllUsersFromDB(){
        db.collection("Persons")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        Vector<Person> personsVector = new Vector<>();
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
