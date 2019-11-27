/* Code follows same idea as Java version
 *
 * This code follows following conventions
 *
 * 0 else
 * 1 level of indentation in functions or less
 * 2 parameters per function or less
 * 3 lines of code per function or less
 *
 */
package de.eckerta.kata

import de.eckerta.kata.Translation.Transformation
import de.eckerta.kata.Translation.TranslationResult

private const val letters = "IVXLCDM"

fun translate(number: Int): String {
    val initialTranslation = Translation(TranslationResult(), number)
    val finalTranslation = doTranslate(initialTranslation)
    return finalTranslation.state.toString()
}

private fun doTranslate(initialTranslation: Translation) : Translation {
    val translatorChain = createTranslatorChain()
    return translatorChain.fold(initialTranslation) { result, translator ->
        translator.translate(result)
    }
}

private fun createTranslatorChain() : List<Translator> {
    val singleDigitTranslators = (0..2).map { LeastSignificantDigitTranslator(2 * it) }
    return singleDigitTranslators + LargeNumberTranslator()
}

private class Translation(val state: TranslationResult, val remaining: Int) {
    fun transform(transformation: Transformation, onlyIf: () -> Boolean = { true }): Translation {
        if (onlyIf())
            return Translation(state.appendToPrefix(transformation.extensionForPrefix), remaining - transformation.subtractionValue)

        return this
    }

    data class TranslationResult(val prefix: String = "", val suffix: String = "") {
        fun appendToPrefix(value: String) = copy(prefix = prefix + value)
        fun prependToSuffix(value: String) = copy(suffix = value + suffix)
        override fun toString() = prefix + suffix
    }

    class Transformation(val extensionForPrefix: String, val subtractionValue: Int)
}

private interface Translator {
    fun translate(translation: Translation): Translation
}

private class LeastSignificantDigitTranslator(letterOffset: Int) : Translator {
    val localLetters = letters.substring(letterOffset, letterOffset + 3).map { it.toString() }
    val smallLetter = localLetters[0]

    override fun translate(translation: Translation): Translation {
        val localTranslation = Translation(TranslationResult(), translation.remaining % 10)
        val localResult =  translateLocal(localTranslation)
        return Translation(translation.state.prependToSuffix(localResult.state.prefix), translation.remaining / 10)
    }

    private fun translateLocal(localTranslation : Translation) : Translation {
        return localTranslation.translateIfUseSubtraction()
                .translateLargeLetter()
                .translateSmallLetter()
    }

    fun Translation.translateIfUseSubtraction(): Translation {
        val transformation = Transformation(smallLetter, -1)
        return transform(transformation) { remaining % 5 == 4 }
    }

    fun Translation.translateLargeLetter(): Translation {
        val fiveCount = remaining / 5
        val transformation = Transformation(localLetters[fiveCount], 5 * fiveCount)
        return transform(transformation) { fiveCount > 0 }
    }

    fun Translation.translateSmallLetter(): Translation {
        val transformation = Transformation(smallLetter.repeat(remaining), remaining)
        return transform(transformation)
    }
}

private class LargeNumberTranslator : Translator {
    override fun translate(translation: Translation): Translation {
        val largeLetter = letters.last().toString()
        val transformation = Transformation(largeLetter.repeat(translation.remaining), translation.remaining)
        return translation.transform(transformation)
    }
}