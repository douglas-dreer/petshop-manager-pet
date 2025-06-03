package br.com.petshop.pets.extensions

/**
 * Verifica se o número inteiro é positivo.
 *
 * @return `true` se o número for maior que zero, caso contrário `false`.
 *
 * Exemplo de uso:
 * ```
 * val numero = 5
 * if (numero.isPositive()) {
 *     println("O número é positivo!")
 * }
 * ```
 */
fun Int.isPositive() = this > 0
