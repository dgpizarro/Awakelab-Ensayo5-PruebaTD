# Awakelab-Ensayo5-PruebaTD
<h3> App web de lectura de posts y comentarios con carga de contenido Json externo. </h3>

<b>Problema </b> 

Un diario electrónico pone a disposición del público sus publicaciones y comentarios de estas, a fin de que se pueda replicar en otros medios, o bien para analítica de datos.

<b>Tablas Base de Datos </b> 
<ul>
  <li>users: almacena los usuarios que realizan las publicaciones. Los campos de la tabla son un identificador, el nombre, el identificador de usuario y el correo electrónico.
 </li>
  <li>posts: en esta tabla se almacenan las publicaciones. Los campos pertenecientes a esta tabla son un identificador del registro, el identificador de usuario, el título de la publicación y el cuerpo o texto de la publicación. </li>
  <li>comments: en esta tabla se almacenarán los comentarios de las publicaciones indicadas en el punto anterior. Los campos asociados a los comentarios son: un identificador, un identificador de la publicación, el título de la publicación, el correo electrónico de quien publica y el cuerpo o texto del comentario. </li>
</ul>

<b>Acceso a Datos Json: </b> 
<ul>
<li>https://jsonplaceholder.typicode.com/posts: Permite obtener el listado de publicaciones en formato JSON </li>
<li>https://jsonplaceholder.typicode.com/comments: Permite obtener el listado de comentarios en formato JSON </li>
</ul>

<b>A través del modelo anterior, se debe crear un sistema web compuesto por tres secciones: </b> 
<ul>
<li> Carga de datos: Se debe crear un controlador que permita cargar los datos de las tablas “posts” y “comments” desde los servicios antes mencionados. Dado que la carga es completa, antes de realizar el proceso debe procurar eliminar los registros de ambas tablas.</li>
<li>< Listado de publicaciones: se debe crear un controlador para desplegar el listado de publicaciones. Debe mostrar el título, el nombre del usuario que la publicó y la cantidad de comentarios asociados a ella./li>
 <li> Listado de comentarios: se debe crear un controlador que despliegue los comentarios de una publicación. Este método debe recibir como parámetro en la URL el id de la publicación a analizar, y en base al mismo debe filtrar los comentarios. Se pide desplegar el campo identificador del comentario, el título o nombre de la publicación y el correo electrónico. Además, se deben mostrar los primeros 20 caracteres del cuerpo del comentario.</li>
</ul>
