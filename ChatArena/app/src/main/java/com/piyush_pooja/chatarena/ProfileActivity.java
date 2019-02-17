package com.piyush_pooja.chatarena;

import android.app.ProgressDialog;
import android.icu.text.DateFormat;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.Date;
import java.util.HashMap;

public class ProfileActivity extends AppCompatActivity {

    private ImageView mProfileImage;
    private TextView mProfileName , mProfileStatus , mProfileFriendsCount;
    private Button mProfileSendReqBtn;
    private Button mProfileDeclineReqBtn;

    private DatabaseReference mUserDatabase;

    private FirebaseUser mCurrentUser;

    private ProgressDialog mProgressDialog;

    private DatabaseReference mFriendReqDatabase;
    private DatabaseReference mFriendDatabase;
    private DatabaseReference mNotificationDatabase;

    private int mCurrent_state;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        final String user_id = getIntent().getStringExtra("user_id");

        mUserDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(user_id);    //Creating Database
        mFriendReqDatabase = FirebaseDatabase.getInstance().getReference().child("Friend_req");
        mFriendDatabase = FirebaseDatabase.getInstance().getReference().child("Friends");
        mNotificationDatabase  =FirebaseDatabase.getInstance().getReference().child("notifications");
        mCurrentUser = FirebaseAuth.getInstance().getCurrentUser();

        mProfileImage = (ImageView) findViewById(R.id.profileImage);
        mProfileName = (TextView) findViewById(R.id.profile_dispalyName);
        mProfileStatus = (TextView) findViewById(R.id.profile_status);
        mProfileFriendsCount = (TextView) findViewById(R.id.profile_totalFriends);
        mProfileSendReqBtn = (Button) findViewById(R.id.profile_sendReqBtn);
        mProfileDeclineReqBtn = (Button) findViewById(R.id.profile_decline_btn);

        mCurrent_state = 0;
        mProfileDeclineReqBtn.setVisibility(View.INVISIBLE);
        mProfileDeclineReqBtn.setEnabled(false);

        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setTitle("Loading User Profile");
        mProgressDialog.setMessage("Please wait while we load User Profile");
        mProgressDialog.setCanceledOnTouchOutside(false);
        mProgressDialog.show();


        mUserDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String display_name = dataSnapshot.child("name").getValue().toString();
                String status = dataSnapshot.child("status").getValue().toString();
                String image = dataSnapshot.child("image").getValue().toString();

                mProfileName.setText(display_name);
                mProfileStatus.setText(status);

                Picasso.with(ProfileActivity.this).load(image).placeholder(R.drawable.defaultavatar).into(mProfileImage);




                //----------------------FRIENDS LIST / REQUEST FEATURE------------------


                mFriendReqDatabase.child(mCurrentUser.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        if (dataSnapshot.hasChild(user_id)){
                            String req_type = dataSnapshot.child(user_id).child("request_type").getValue().toString();

                            if (req_type.equals("received")){

                                mCurrent_state = 2;
                                mProfileSendReqBtn.setText("Accept Friend Request");                      // mCurrent_state = 0 = no request sent.
                                mProfileDeclineReqBtn.setVisibility(View.VISIBLE);
                                mProfileDeclineReqBtn.setEnabled(true);                                   //mCurrent = 1 = request sent
                                                                                                        //mcurrent = 2 = req received
                                                                                                        //mcurrent = 3 = friends

                            }else if (req_type.equals("sent")){

                                mCurrent_state = 1;
                                mProfileSendReqBtn.setText("Cancel Friend Request");
                                mProfileDeclineReqBtn.setVisibility(View.INVISIBLE);
                                mProfileDeclineReqBtn.setEnabled(false);


                            }
                            mProgressDialog.dismiss();
                        }else{
                            mFriendDatabase.child(mCurrentUser.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    if (dataSnapshot.hasChild(user_id)){

                                        mCurrent_state = 3;
                                        mProfileSendReqBtn.setText("Unfriend this Person");
                                        mProfileDeclineReqBtn.setVisibility(View.INVISIBLE);
                                        mProfileDeclineReqBtn.setEnabled(false);

                                    }
                                    mProgressDialog.dismiss();
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {
                                    mProgressDialog.dismiss();
                                }
                            });
                        }



                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });




            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        mProfileSendReqBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mProfileSendReqBtn.setEnabled(false);


                //-----------------NOT FRIENDS STATE-------------------------//

                if(mCurrent_state == 0){


                    mFriendReqDatabase.child(mCurrentUser.getUid()).child(user_id).child("request_type").setValue("sent").addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            if (task.isSuccessful()){

                                mFriendReqDatabase.child(user_id).child(mCurrentUser.getUid()).child("request_type")
                                        .setValue("received").addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {

                                        HashMap<String , String>  notificationData = new HashMap<String, String>();
                                        notificationData.put("from",mCurrentUser.getUid());
                                        notificationData.put("type","request");

                                        mNotificationDatabase.child(user_id).push().setValue(notificationData).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()){

                                                    mCurrent_state = 1;
                                                    mProfileSendReqBtn.setText("Cancel Friend Request");
                                                    mProfileDeclineReqBtn.setVisibility(View.INVISIBLE);
                                                    mProfileDeclineReqBtn.setEnabled(false);


                                                }
                                            }
                                        });


                                      //  Toast.makeText(ProfileActivity.this, "Success Sending Request", Toast.LENGTH_SHORT).show();

                                    }
                                });
                                
                            }else {
                                Toast.makeText(ProfileActivity.this, "Failed Sending Request", Toast.LENGTH_SHORT).show();
                            }
                            mProfileSendReqBtn.setEnabled(true);
                        }


                    });


                }

                //-----------------CANCEL FRIENDS REQ STATE-------------------------//

                   if (mCurrent_state == 1){

                       mFriendReqDatabase.child(mCurrentUser.getUid()).child(user_id).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                           @Override
                           public void onSuccess(Void aVoid) {

                               mFriendReqDatabase.child(user_id).child(mCurrentUser.getUid()).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                                   @Override
                                   public void onSuccess(Void aVoid) {

                                       mProfileSendReqBtn.setEnabled(true);
                                       mCurrent_state = 0;
                                       mProfileSendReqBtn.setText("Send Friend Request");
                                       mProfileDeclineReqBtn.setVisibility(View.INVISIBLE);
                                       mProfileDeclineReqBtn.setEnabled(false);

                                   }
                               });
                           }
                       });
                   }



                //----------------REQ RECEIVED STATE-----------//
                if (mCurrent_state == 2){

                    final String currentDate =java.text.DateFormat.getDateTimeInstance().format(new Date());

                    mFriendDatabase.child(mCurrentUser.getUid()).child(user_id).setValue(currentDate).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            if (task.isSuccessful()){

                                mFriendDatabase.child(user_id).child(mCurrentUser.getUid()).setValue(currentDate).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {

                                        if (task.isSuccessful())

                                        mFriendReqDatabase.child(mCurrentUser.getUid()).child(user_id).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {

                                                if (task.isSuccessful()){

                                                    mFriendReqDatabase.child(user_id).child(mCurrentUser.getUid()).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                                                        @Override
                                                        public void onSuccess(Void aVoid) {


                                                            mProfileSendReqBtn.setEnabled(true);
                                                            mCurrent_state = 3;
                                                            mProfileSendReqBtn.setText("Unfriend this Person");
                                                            mProfileDeclineReqBtn.setVisibility(View.INVISIBLE);
                                                            mProfileDeclineReqBtn.setEnabled(false);

                                                        }
                                                    });
                                                }

                                            }
                                        });



                                    }
                                });

                            }
                        }
                    });

                }

                //------Cancel THE REQUEST----//

                if (mCurrent_state==3){
                    mFriendDatabase.child(mCurrentUser.getUid()).child(user_id).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            if (task.isSuccessful()){

                                mFriendDatabase.child(user_id).child(mCurrentUser.getUid()).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {

                                        mProfileSendReqBtn.setEnabled(true);
                                        mCurrent_state = 0;
                                        mProfileSendReqBtn.setText("Send Request Again");
                                        mProfileDeclineReqBtn.setVisibility(View.INVISIBLE);
                                        mProfileDeclineReqBtn.setEnabled(false);

                                    }
                                });
                            }

                        }
                    });
                }
            }
        });

                //-----REJECTING THE REQUEST----//

            mProfileDeclineReqBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (mCurrent_state == 2 ) {

                            mProgressDialog.show();
                        mFriendReqDatabase.child(mCurrentUser.getUid()).child(user_id).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                                if (task.isSuccessful()){

                                    mFriendReqDatabase.child(user_id).child(mCurrentUser.getUid()).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {

                                            mProfileSendReqBtn.setEnabled(true);
                                            mProgressDialog.dismiss();
                                            mCurrent_state = 0;
                                            mProfileSendReqBtn.setText("Send Friend Request");
                                            mProfileDeclineReqBtn.setVisibility(View.INVISIBLE);
                                            mProfileDeclineReqBtn.setEnabled(false);



                                        }
                                    });
                                }

                            }
                        });
                    }
                }
            });


    }
}
