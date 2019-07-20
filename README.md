# Ud4_Ejemplo3
_Ejemplo 3 de la Unidad 4._ 

Vamos a implementar la misma aplicación que en [Ud4_Ejemplo2](https://github.com/Fpcarlosc/Ud4_Ejemplo2) pero utilizando 
la clase _RecyclerView_ y sin eventos.

Para ello vamos a seguir los siguientes pasos:

## Paso 1: Creación de los _layouts_

Primero vamos a crear la lista usando _RecyclerView_ en el fichero _activity_main.xml_:
```
<android.support.constraint.ConstraintLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">

    <android.support.v7.widget.RecyclerView
        android:id="@+id/recyclerView"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toRightOf="parent"
        app:layout_constraintTop_toTopOf="parent" />

</android.support.constraint.ConstraintLayout>
```
Después, al igual que en el ejemplo 2, necesitamos crear otro layout ya que la lista va a contener dos _TextView_, 
uno para el nombre y otro para indicar dónde apareció, cada uno con su _id_.

_elementos_lista.xml_:
```
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:orientation="vertical"
    android:padding="16dp">

    <TextView
        android:id="@+id/nombreTextView"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        tools:text="nombre"/>

    <TextView
        android:id="@+id/aparicionTextView"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        tools:text="aparición"/>

</LinearLayout>
```

Observad que se utiliza el atributo _text_ del espacio de nombres _tools_ para ver cómo queda el diseño.

## Paso 2: Creación de la clase Vehículo

Como cada elemento de la lista va a contener más de un dato relacionado con el vehículo, vamos a crear una clase con dos atributos, uno para el nombre y otro para la aparición.

_Vehiculo.java_:
```
public class Vehiculo {
    private String nombre; // Nombre del vehículo.
    private String aparicion; // Serie o película donde aparece.

    public Vehiculo(String nombre, String aparicion) {
        this.nombre = nombre;
        this.aparicion = aparicion;
    }

    public String getNombre() {
        return nombre;
    }

    public String getAparicion() {
        return aparicion;
    }
}
```

## Paso 3: Creación del adaptador _VehiculoAdapter_

El siguiente paso es crear el adaptador que le pasará las _Views_ a _RecyclerView_. 
Éste heredará de _RecyclerView.Adapter_ de tipo _VehiculoAdapter.MiViewHolder_ donde _MiViewHolder_ es una _Inner class_ que nos
crearemos dentro de la clase _VehiculoAdapter_, esta clase solo contendrá los _TextViews_ de cada elemento de la lista.

Además dentro de la clase _VehiculoAdapter_ tendremos que sobreescribir los siguientes métodos:
+ _onCreateViewHolder_: Construye un nuevo _ViewHolder_ con las nuevas _Views_ recicladas, en este caso "inflándolas" desde _elementos_lista.xml_.
+ _onBindViewHolder_: Muestra el elemento de la lista creado anteriormente (las _Views_ recicladas con los nuevos valores).
+ _getItemCount_: Devuelve el número total de elementos de la lista.

_VehiculoAdapter.java_:
```
public class VehiculoAdapter extends RecyclerView.Adapter<VehiculoAdapter.MiViewHolder> {

    private ArrayList<Vehiculo> lista;

    public VehiculoAdapter(ArrayList<Vehiculo> lista) {
        this.lista = lista;
    }

    @NonNull
    @Override
    public VehiculoAdapter.MiViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        // Creamos las views de los vehículos
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());

        View view = inflater.inflate(R.layout.elementos_lista, viewGroup, false);

        MiViewHolder miViewHolder = new MiViewHolder(view);

        return miViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull VehiculoAdapter.MiViewHolder holder, int position) {
        // Establecemos el nombre y aparición para el vehículo de esa posición
        String nombre = lista.get(position).getNombre();

        holder.nombretextView.setText(nombre);

        String aparicion = lista.get(position).getAparicion();

        holder.apariciontextView.setText(aparicion);
    }

    @Override
    public int getItemCount() {
        if (lista == null)
            return 0;
        else
            return lista.size();
    }


    public static class MiViewHolder extends RecyclerView.ViewHolder {
        public TextView nombretextView;
        public TextView apariciontextView;

        public MiViewHolder(View view) {
            super(view);

            nombretextView = itemView.findViewById(R.id.nombreTextView);
            apariciontextView = itemView.findViewById(R.id.aparicionTextView);
        }
    }
}
```
## Paso 4: Implementación de la clase _MainActivity_

El último paso es implementar la clase _MainActivity_ donde incluimos el _ArrayList_ de tipo _Vehiculo_ y añadimos sus elementos, 
buscamos el _RecyclerView_, indicamos que su tamaño es fijo y añadimos la línea divisoria entre elementos. 
Después creamos un _LinearLayout_ que contendrá cada elemento del _RecyclerView_ y por último creamos el _adapter_ y lo asignamos al _RecyclerView_.

_MainActivity.java_:
```
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Creamos el array de vehiculos.
        ArrayList<Vehiculo> vehiculos = new ArrayList<>();

        vehiculos.add(new Vehiculo("Ecto1", "Los cazafantasmas"));
        vehiculos.add(new Vehiculo("DeLorean", "Regreso al futuro"));
        vehiculos.add(new Vehiculo("Kitt", "El coche fantástico"));
        vehiculos.add(new Vehiculo("Halcón Milenario", "Star Wars"));
        vehiculos.add(new Vehiculo("Planet Express", "Futurama"));
        vehiculos.add(new Vehiculo("TARDIS", "Doctor Who"));
        vehiculos.add(new Vehiculo("USS Enterprise", "Star Trek"));
        vehiculos.add(new Vehiculo("Nabucodonosor", "Matrix"));
        vehiculos.add(new Vehiculo("Odiseus", "Ulises 31"));
        vehiculos.add(new Vehiculo("Nostromo", "Alien"));

        // Buscamos el RecyclerView e indicamos que su tamaño es fijo
        RecyclerView recycler = findViewById(R.id.recyclerView);

        recycler.setHasFixedSize(true);

        // Añadimos la línea de separación de elementos de la lista
        // 0 para horizontal y 1 para vertical
        recycler.addItemDecoration(new DividerItemDecoration(MainActivity.this, 1));

        // Creamos un LinearLayout que contendrá cada elemento del RecyclerView
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycler.setLayoutManager(linearLayoutManager);

        // Creamos el adapter y lo asignamos al RecyclerView
        VehiculoAdapter adapter = new VehiculoAdapter(vehiculos);

        recycler.setAdapter(adapter);

    }
}
```

Para poder utilizar la clase _RecyclerView_ deberemos incluir la dependencia _com.android.support:recyclerview-v7:28.0.0_ 
en el fichero _build.gradle(Module:app)_:
```
dependencies {
    implementation fileTree(dir: 'libs', include: ['*.jar'])
    implementation 'com.android.support:appcompat-v7:28.0.0'
    implementation 'com.android.support.constraint:constraint-layout:1.1.3'
    implementation 'com.android.support:recyclerview-v7:28.0.0'
    ...
}
```
