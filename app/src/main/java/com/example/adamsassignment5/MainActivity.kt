package com.example.adamsassignment5

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import com.example.adamsassignment5.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var secret = ""
    private var numGuesses = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        clearGame()
        simpleMessage(Color.BLACK, R.string.welcome_msg)

        binding.buttonStart.setOnClickListener(
            View.OnClickListener {
                val num = (0..500).random()
                val secretWord = wordList[num]
                secret = secretWord
                simpleMessage(Color.BLACK, R.string.begin_msg)
                startGame()
                Log.i("Word: ", secret)
            }
        )
        binding.buttonLetterOne.setOnClickListener(
            View.OnClickListener {
                val guessChar = binding.editTextGuess.getText().toString().trim()
                checkGuess(guessChar, binding.buttonLetterOne)
            }
        )
        binding.buttonLetterTwo.setOnClickListener(
            View.OnClickListener {
                val guessChar = binding.editTextGuess.getText().toString().trim()
                checkGuess(guessChar, binding.buttonLetterTwo)
            }
        )
        binding.buttonLetterThree.setOnClickListener(
            View.OnClickListener {
                val guessChar = binding.editTextGuess.getText().toString().trim()
                checkGuess(guessChar, binding.buttonLetterThree)
            }
        )
        binding.buttonLetterFour.setOnClickListener(
            View.OnClickListener {
                val guessChar = binding.editTextGuess.getText().toString().trim()
                checkGuess(guessChar, binding.buttonLetterFour)
            }
        )
        binding.buttonCheck.setOnClickListener(
            View.OnClickListener {
                val one = binding.buttonLetterOne.getText().toString().lowercase()
                if (one != (secret[0].toString())) {
                    binding.buttonLetterOne.setText(R.string.placeholder)
                }
                val two = binding.buttonLetterTwo.getText().toString().lowercase()
                if (two != (secret[1].toString())) {
                    binding.buttonLetterTwo.setText(R.string.placeholder)
                }
                val three = binding.buttonLetterThree.getText().toString().lowercase()
                if (three != (secret[2].toString())) {
                    binding.buttonLetterThree.setText(R.string.placeholder)
                }
                val four = binding.buttonLetterFour.getText().toString().lowercase()
                if (four != (secret[3].toString())) {
                    binding.buttonLetterFour.setText(R.string.placeholder)
                }
                val answer = "$one$two$three$four"
                if (answer == secret) {
                    val message = getResources().getString(R.string.winning_msg, numGuesses)
                    detailMessage(Color.BLUE, message)
                    clearGame()
                } else {
                    simpleMessage(Color.BLACK, R.string.incorrect)
                }
            }
        )
        binding.buttonGiveUp.setOnClickListener(
            View.OnClickListener {
                val message = getResources().getString(R.string.give_up_msg, secret, numGuesses)
                detailMessage(Color.BLACK, message)
                clearGame()
            }
        )
    }

    private fun simpleMessage(textColor: Int, setText: Int) {
        binding.textViewMessage.setTextColor(textColor)
        binding.textViewMessage.setText(setText)
    }

    private fun detailMessage(textColor: Int, setText: String) {
        binding.textViewMessage.setTextColor(textColor)
        binding.textViewMessage.setText(setText)
    }

    private fun checkGuess(guessChar: String, button: Button) {
        when {
            guessChar.matches(Regex("""[^A-Za-z]""")) -> {
                simpleMessage(Color.RED, R.string.not_letter_error)
                binding.editTextGuess.setText(R.string.clear)
            }
            guessChar.isEmpty() -> {
                simpleMessage(Color.RED, R.string.whitespace_error)
                binding.editTextGuess.setText(R.string.clear)
            }
            guessChar.length > 1 -> {
                simpleMessage(Color.RED, R.string.multi_letter_error)
                binding.editTextGuess.setText(R.string.clear)
            }
            else -> {
                numGuesses++
                simpleMessage(Color.BLACK, R.string.clear)
                binding.editTextGuess.setText(R.string.clear)
                if (button == binding.buttonLetterOne) {
                    binding.buttonLetterOne.setText(guessChar)
                }
                if (button == binding.buttonLetterTwo) {
                    binding.buttonLetterTwo.setText(guessChar)
                }
                if (button == binding.buttonLetterThree) {
                    binding.buttonLetterThree.setText(guessChar)
                }
                if (button == binding.buttonLetterFour) {
                    binding.buttonLetterFour.setText(guessChar)
                }

                val guess = mutableListOf(
                    binding.buttonLetterOne.getText().toString().lowercase(),
                    binding.buttonLetterTwo.getText().toString().lowercase(),
                    binding.buttonLetterThree.getText().toString().lowercase(),
                    binding.buttonLetterFour.getText().toString().lowercase()
                )
                Log.i("Guess: ", guess.toString())
            }
        }
    }

    private fun startGame() {
        binding.buttonStart.isEnabled = false
        binding.buttonLetterOne.isEnabled = true
        binding.buttonLetterTwo.isEnabled = true
        binding.buttonLetterThree.isEnabled = true
        binding.buttonLetterFour.isEnabled = true
        binding.buttonGiveUp.isEnabled = true
        binding.buttonCheck.isEnabled = true
        binding.editTextGuess.isEnabled = true
    }

    private fun clearGame() {
        binding.buttonStart.isEnabled = true
        binding.buttonLetterOne.isEnabled = false
        binding.buttonLetterTwo.isEnabled = false
        binding.buttonLetterThree.isEnabled = false
        binding.buttonLetterFour.isEnabled = false
        binding.buttonGiveUp.isEnabled = false
        binding.buttonCheck.isEnabled = false
        binding.editTextGuess.isEnabled = false
        binding.buttonLetterOne.setText(R.string.placeholder)
        binding.buttonLetterTwo.setText(R.string.placeholder)
        binding.buttonLetterThree.setText(R.string.placeholder)
        binding.buttonLetterFour.setText(R.string.placeholder)
        binding.editTextGuess.setText(R.string.clear)
        numGuesses = 0
    }

    val wordList = listOf(
        "with", "that", "from", "were", "this", "also", "they", "have", "been", "when",
        "into", "time", "more", "over", "only", "most", "some", "city", "such", "then",
        "made", "used", "many", "year", "part", "born", "film", "than", "team", "both",
        "well", "them", "work", "life", "area", "name", "high", "will", "four", "each",
        "same", "game", "club", "june", "july", "west", "john", "home", "town", "held",
        "york", "took", "line", "east", "song", "best", "back", "like", "show", "band",
        "main", "left", "said", "died", "last", "five", "long", "very", "park", "road",
        "army", "book", "what", "near", "just", "late", "form", "much", "side", "play",
        "down", "even", "land", "came", "went", "make", "next", "role", "king", "head",
        "list", "take", "site", "days", "lost", "live", "open", "once", "hall", "race",
        "lead", "case", "wife", "lake", "upon", "rock", "free", "good", "love", "tour",
        "away", "body", "sold", "less", "gave", "term", "fire", "does", "arts", "half",
        "help", "seen", "full", "thus", "must", "news", "able", "gold", "sent", "soon",
        "bank", "week", "base", "data", "post", "type", "paul", "size", "hill", "star",
        "goal", "runs", "real", "come", "food", "ship", "navy", "unit", "whom", "ever",
        "plan", "blue", "bill", "past", "find", "date", "here", "mark", "move", "test",
        "rest", "seat", "room", "loss", "wide", "fact", "give", "nine", "port", "lord",
        "uses", "word", "hand", "mary", "fort", "need", "rate", "fall", "turn", "deal",
        "shot", "care", "plot", "cast", "camp", "male", "done", "cost", "view", "vote",
        "face", "lies", "call", "rule", "told", "pass", "idea", "wall", "dead", "dark",
        "code", "host", "grew", "your", "lack", "ohio", "wing", "poor", "true", "farm",
        "mass", "keep", "meet", "girl", "crew", "jack", "solo", "asia", "stop", "cars",
        "join", "hold", "firm", "iron", "wood", "hard", "fell", "know", "fish", "sons",
        "feet", "duke", "tree", "arms", "rose", "iran", "draw", "ball", "rail", "zone",
        "text", "fine", "lady", "note", "kept", "paid", "says", "deep", "felt", "goes",
        "jazz", "laws", "cell", "hong", "mile", "kong", "fame", "rome", "boys", "read",
        "acts", "look", "vice", "fans", "mike", "peak", "rank", "risk", "ring", "kind",
        "holy", "want", "miss", "fund", "sets", "drug", "sign", "rise", "edge", "ways",
        "guns", "bowl", "beat", "stay", "onto", "pope", "cape", "wild", "mill", "bass",
        "ends", "wins", "rare", "pair", "boat", "core", "wind", "heat", "rear", "aged",
        "page", "kill", "jews", "card", "loan", "bell", "sale", "ncaa", "fuel", "poet",
        "rich", "mind", "roof", "cold", "hits", "laid", "tony", "mine", "earl", "flow",
        "gene", "ford", "ward", "save", "fair", "task", "flag", "spot", "hope", "khan",
        "coal", "foot", "talk", "wars", "duty", "gain", "hour", "dean", "folk", "prix",
        "link", "plus", "ones", "gets", "shop", "moth", "lane", "drew", "path", "door",
        "golf", "arab", "fast", "eyes", "jean", "bird", "mean", "iowa", "trip", "flat",
        "ages", "step", "sell", "wave", "juan", "baby", "soul", "acid", "iraq", "feel",
        "moon", "gulf", "grey", "adam", "soil", "bear", "pool", "tank", "wine", "tell",
        "grow", "none", "anne", "alan", "gone", "salt", "user", "poem", "huge", "walk",
        "rice", "ryan", "jobs", "eric", "anna", "tied", "tall", "hair", "gate", "mode",
        "safe", "easy", "skin", "bush", "kent", "dave", "carl", "bond", "feed", "alex",
        "slow", "ross", "knew", "jane", "nazi", "pick", "file", "matt", "hero", "fear",
        "twin", "snow", "nick", "rain", "cash", "fifa", "utah", "wear", "seek", "disc",
        "ride", "send", "gang", "sees", "drop", "evil", "roll", "gary", "pain", "andy",
        "seed", "jeff", "bomb", "uefa", "gray", "jury", "cave", "suit", "clay", "yard",
        "hull", "nova", "clan", "legs", "zero", "lose", "mall", "tail", "asks", "rico",
        "hunt", "sand", "sole", "cook", "fled", "fred", "mail", "else", "sang", "belt",
        "vast", "till", "karl", "debt", "copy", "soft", "ties", "jump", "eggs", "tend",
        "tool", "pole", "seem", "maps", "dogs", "cuba", "tech", "coat", "vary", "pure",
        "sing", "kids", "loop", "phil", "tons", "exit", "pink", "peru", "root", "hole"
    )
}