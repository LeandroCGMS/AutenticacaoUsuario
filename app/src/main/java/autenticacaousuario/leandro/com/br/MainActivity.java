package autenticacaousuario.leandro.com.br;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth = FirebaseAuth.getInstance();

        //Login de usuário
        firebaseAuth.signInWithEmailAndPassword("leandro.curso@gmail.com","leandro123")
            .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {//Sucesso ao cadastrar usuário
                        Log.i("signIn","Sucesso ao logar usuário.");
                    } else {//Erro ao cadastrar usuáriop
                        Log.i("signIn","Erro ao logar usuário." + task.getException());

                    }
                }
            });

        //Cadastro
        /*firebaseAuth.createUserWithEmailAndPassword("leandro.curso@gmail.com",
                "leandro123").addOnCompleteListener(MainActivity.this,
                new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {//Sucesso ao cadastrar usuário
                            Log.i("createUser","Sucesso ao cadastrar usuário.");
                        } else {//Erro ao cadastrar usuáriop
                            Log.i("createUser","Erro ao cadastrar usuário.");

                        }
                    }
                });*/

        Usuario usuario1 = new Usuario();
        usuario1.setNome("Leandro");
        usuario1.setSenha("12345678");

        databaseReference.child("usuarios").setValue(usuario1);
    }
}
