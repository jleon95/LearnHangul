# LearnHangul

It's been almost four years since I started learning Japanese in my free time. I have reached a point in my progress now from where I can look back and spot some things that have been truly useful, although there's still a lot more to learn before I can consider myself proficient.

One of the challenges when learning a language is dealing with massive amounts of vocabulary and, in some instances, new alphabets, syllabaries or even sets of characters with their own associated meanings like Kanji, Hanzi or Hanja. Against the need to repeat those items many times until they stick in your mind, I've found two approaches to be particularly effective: Spaced Repetition and gamification. This is about the latter.

Some months ago, I started to take an interest in learning Korean, although that must be once I'm proficient enough in Japanese (as long as learning these two languages at the same time may hamper the process). Keeping that in mind and having had some experience with Android programming, it dawned on me that perhaps I could implement what had already been proven useful to me in Japanese.

Drawing heavy inspiration from a great Android app I use every day to review Japanese vocabulary [[**Obenkyo**](https://play.google.com/store/apps/details?id=com.Obenkyo "Obenkyo")], I decided I'd emulate what it does best in an app of my own doing, thus killing two birds with one stone. As the similarities with **Obenkyo** and other apps with the same purpose are not few, this won't be released and will remain only as a (more than I expected) useful programming exercise.

The first thing one has to do in order to learn a language like Korean effectively is get a basic understanding of how it's written. Given that in Hangul, its scripting alphabet, there are only so many distinct symbols that we can combine to form words, gamification can do a lot to quickly familiarize ourselves with them. Due to this app being intended for beginners, a word of caution is needed: each character is associated with a transcription, but one should abandon this association and start understanding them in the context of the language and its phonetic rules once they feel familiar enough.

Because trying to memorize all of them at once can be overwhelming, we have the option of selecting which ones we want for the moment. The color in which they are drawn changes depending on your level of proficiency with that character (from best to worst: green, yellow, orange and red); it also displays a darker tone if it's not currently selected (grey if never selected):

![characters_consonants](https://user-images.githubusercontent.com/10140054/28382993-4a1844d4-6cc0-11e7-938b-4dae7355adda.jpg)

Each time you answer correctly in a quiz involving a certain character, you'll get an increment of its score; in an equivalent manner, if you fail, your score will drop.

Having selected some of them from the vowels and consonants lists (at least two, it's not fun if you already know what possibilities there will be), it's time to go to the quizzes. The **Review** section lets you choose from four quizzes.

![review](https://user-images.githubusercontent.com/10140054/28382990-49f1cfe8-6cc0-11e7-85f1-7d4eb13d6a0c.jpg)

But let's stick to the first two for now. If you're asked to find a transcription, you'll be given a character and six possible answers. Conversely, if you're asked to find a character, you'll be given a transcription and six possible answers. Let's see an example of the latter:

![choose_character](https://user-images.githubusercontent.com/10140054/28382994-4a20abb0-6cc0-11e7-9d48-4166cfc0e712.jpg)

By the way, the answer is in the upper right corner button.

Now, why have we put aside the last two quizzes? That's because the only characters that will appear there are real-life syllables, and it's convenient that you have a good grasp of all the vowels and consonants if you don't want to end up relying on blind guesses. Also, you have to know how these individual elements are arranged inside a block in order to form a correct syllable. You can get a rough idea by browsing the internet or by looking at the **About Hangul** section, written so that you don't have to turn to Wikipedia each time you have a doubt:

![about_hangul](https://user-images.githubusercontent.com/10140054/28382991-49fe0ca4-6cc0-11e7-8a05-199ddee41a1b.jpg)

Finally, an example of a quiz with syllables:

![choose_syllable_transcription](https://user-images.githubusercontent.com/10140054/28382992-4a08b924-6cc0-11e7-9d93-815f90cbaa4a.jpg)

By the way again, the correct answer is the first.

And that's all, I hope it's useful (at least for me).
