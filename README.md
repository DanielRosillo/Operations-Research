# Operations-Research

#  Savage

![Leonard-J -Savage](https://user-images.githubusercontent.com/23446483/62672344-ecf4ab00-b95f-11e9-907d-68ce959cbaf8.jpg)

Leonard Jimmie Savage (1917-1971)- Este criterio ajusta al agente a las decisiones pesimistas y conservadoras, la matriz de ganancia esta basada en el costo de oportunidad, evalúa en que perdidas se incurre si no se escoge la mejor decisión, una vez tomada la decisión y producido el estado natural, se obtiene un resultado; Savage argumenta que después de conocer el resultado el decisor puede arrepentirse de haber seleccionado la alternativa dada.

## Algoritmo<br>
1.- Determinar para cada estado de la naturaleza la mejor ganancia.<br>
2.- Calcular el costo de oportunidad para cada alternativa, como la diferencia entre su ganancia y la mejor calculada. Una vez calculadas las diferencias se hace una nueva matriz en sustitución de los valores del resto de la columna, esta diferencia es el costo de oportunidad o arrepentimiento por no haber escogido la alternativa que diera el valor óptimo.<br>
3.- Generar un nuevo vector, escogiendo la mejor opción de cada alternativa.<br>
4.- La alternativa con el menor valor dentro del vector es la respuesta.<br>

# Laplace


![Pierre-Simon,_marquis_de_Laplace_(1745-1827)_-_Guérin](https://user-images.githubusercontent.com/23446483/62673310-98ebc580-b963-11e9-8154-d163c990ed67.jpg)

Este criterio puede ser utilizado por un agente que no sea optimista ni pesimista, por que asume que todos los estados de la naturaleza son equiprobables, propone que las probabilidades dadas sean las mismas para cada estado expresado por 1/n, siendo n el número de estados.<br>

## Algoritmo <br>
1.- Calcular la sumatoria de las probabilidades de cada alternativa por el valor a priori del estado.<br>
2.- Del vector resultante, escoger el mayor valor.<br>

# Hurwicz

![225px-Leonid_Hurwicz](https://user-images.githubusercontent.com/23446483/62673652-d13fd380-b964-11e9-9ab0-8a0fd17fc528.jpg)

Criterio intermedio entre el Maximax y Wald, dado que muy pocos agentes son tan pesimistas.<br>
Se eligen los mejores y peores estados por alternativa, hecho esto se aplica un factor de ponderación, para llegar a la mejor decisión mediante el cálculo de utilidades esperado.<br>

## Algoritmo <br>
1.- Obtener los mejores y peores estados por alternativa.<br>
2.- Multiplicar por el factor de ponderación al mas alto y por el factor de ponderación-1, al más bajo.<br>
3.- Escoger la que contenga el mayor resultado.<br>

### Requirements

### Powered by Java JDK 10
![36222735_1731650116889496_8672753406986682368_n](https://user-images.githubusercontent.com/23446483/41886236-26f7ba94-78c1-11e8-963a-cae5eccb6394.jpg)

The Library requires Java SE Development Kit 10 or Higher.

## Authors

 **Daniel Rosillo** - *Initial work* -
 Danielrosillodev@gmail.com

## License

This project is licensed under the MIT License - see the [LICENSE.md] file for details.

## Support && Feedback
https://twitter.com/DanielRosillo22
