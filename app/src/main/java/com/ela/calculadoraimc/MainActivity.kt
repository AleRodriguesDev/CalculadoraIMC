package com.ela.calculadoraimc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.ela.calculadoraimc.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.title = "Calculadora de IMC"
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bt_calcular = binding.btCalcular
        val resultado = binding.resultado

        bt_calcular.setOnClickListener{
            val editPeso = binding.editPeso.text.toString()
            val editAltura = binding.editAltura.text.toString()

            if(editPeso.isEmpty()){
                resultado.setText("Informe o seu peso!")
            }else if(editAltura.isEmpty()){
                resultado.setText("Informe a sua altura!")
            }else{
                CalculoDeIMC()
            }
        }
    }
    private fun CalculoDeIMC(){
        val pesoID = binding.editPeso
        val alturaID = binding.editAltura

        val peso = Integer.parseInt(pesoID.text.toString())
        val altura = java.lang.Float.parseFloat(alturaID.text.toString())
        val imc = peso / (altura * altura)
        val resultado = binding.resultado

        val Mensagem = when{
            imc <= 18.5 -> "Peso baixo"
            imc <= 24.9 -> "Peso normal"
            imc <= 29.9 -> "Sobrepeso"
            imc <= 34.9 -> "Obesidade (Grau 1)"
            imc <= 39.9 -> "Obesidade (Grau 2)"
            else -> "Obesidade Mórbida (Grau 3)"
        }
        imc.toString()
        resultado.setText("IMC: $imc \n $Mensagem")
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflate = menuInflater
        inflate.inflate(R.menu.menu_principal, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.reset -> {
                val limparEditPeso = binding.editPeso
                val limparEditAltura = binding.editAltura
                val limparResultado = binding.resultado

                limparEditPeso.setText("")
                limparEditAltura.setText("")
                limparResultado.setText("")
            }
        }
        return super.onOptionsItemSelected(item)
    }
}