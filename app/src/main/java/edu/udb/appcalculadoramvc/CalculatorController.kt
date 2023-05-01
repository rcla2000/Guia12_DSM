package edu.udb.appcalculadoramvc

import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class CalculatorController(private val view: MainActivity) {
        private val model = CalculatorModel()

        init {
            // Asociar eventos de clic a los botones
            view.findViewById<Button>(R.id.btn_add).setOnClickListener { performOperation("add") }
            view.findViewById<Button>(R.id.btn_subtract).setOnClickListener { performOperation("subtract") }
            view.findViewById<Button>(R.id.btn_multiply).setOnClickListener { performOperation("multiply") }
            view.findViewById<Button>(R.id.btn_divide).setOnClickListener { performOperation("divide") }
            view.findViewById<Button>(R.id.btn_sqrt).setOnClickListener { performOperation("sqrt") }
        }

        private fun errorNum1() : Int {
            if (view.findViewById<EditText>(R.id.num1).text.toString().isEmpty()) {
                view.findViewById<EditText>(R.id.num1).setError("Ingrese un número")
                return 1
            }
            return 0
        }

        private fun errorNum2() : Int {
            if (view.findViewById<EditText>(R.id.num2).text.toString().isEmpty()) {
                view.findViewById<EditText>(R.id.num2).setError("Ingrese un número")
                return 1
            }
            return 0
        }
        private fun performOperation(operation: String) {
            // Obtener los números ingresados por el usuario
            var num1: Double = 0.0
            var num2 : Double = 0.0
            var errores : Int = 0

            errores += errorNum1()
            if (errorNum1() == 0) {
                num1 = view.findViewById<EditText>(R.id.num1).text.toString().toDouble()
            }

            if (operation != "sqrt") {
                errores += errorNum2()
                if (errorNum2() == 0) {
                    num2 = view.findViewById<EditText>(R.id.num2).text.toString().toDouble()
                }
            }

            if (errores == 0) {
                // Realizar la operación correspondiente
                val result = when (operation) {
                    "add" -> model.add(num1, num2)
                    "subtract" -> model.subtract(num1, num2)
                    "multiply" -> model.multiply(num1, num2)
                    "divide" -> model.divide(num1, num2)
                    "sqrt" -> model.sqrt(num1)
                    else -> throw IllegalArgumentException("Operación no válida")
                }

                // Actualizar la vista con el resultado
                view.findViewById<TextView>(R.id.result).text = "Resultado: $result"
            }

        }
}
