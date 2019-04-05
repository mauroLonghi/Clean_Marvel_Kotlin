package com.puzzlebench.clean_marvel_kotlin.data.service

import com.puzzlebench.clean_marvel_kotlin.data.mapper.CharacterMapperService
import com.puzzlebench.clean_marvel_kotlin.data.service.api.MarvelApi
import com.puzzlebench.clean_marvel_kotlin.domain.contracts.CharacterService
import com.puzzlebench.clean_marvel_kotlin.domain.model.Character
import io.reactivex.Observable

class CharacterServicesImpl(private val api: MarvelResquestGenerator = MarvelResquestGenerator()
                            , private val mapper: CharacterMapperService = CharacterMapperService()) : CharacterService {

    override fun getCaracters(): Observable<List<Character>> {
        return Observable.create { subscriber ->
            val callResponse = api.createService(MarvelApi::class.java).getCharacter()
            val response = callResponse.execute()

            if (response.isSuccessful) {
                response.body()?.data?.let {
                    subscriber.onNext(mapper.transform(it.characters))
                    subscriber.onComplete()
                }
            } else {
                subscriber.onError(Throwable(response.message()))
            }
        }
    }

    override fun getCaracters(id: Int): Observable<Character> {
        return Observable.create { subscriber ->
            val callResponse = api.createService(MarvelApi::class.java)
                    .getCharacterById(id)
            val response = callResponse.execute()

            if (response.isSuccessful) {
                response.body()?.data?.let {
                    subscriber.onNext(mapper.transform(it.characters[0]))
                    subscriber.onComplete()
                }
            } else {
                subscriber.onError(Throwable(response.message()))
            }
        }
    }
}
