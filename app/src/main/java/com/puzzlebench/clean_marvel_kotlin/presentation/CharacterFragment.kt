package com.puzzlebench.clean_marvel_kotlin.presentation

import android.app.Dialog
import android.app.DialogFragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.puzzlebench.clean_marvel_kotlin.data.service.CharacterServicesImpl
import com.puzzlebench.clean_marvel_kotlin.domain.usecase.GetCharacterByIdUseCase
import com.puzzlebench.clean_marvel_kotlin.presentation.mvp.CharacterFragmentPresenter
import com.puzzlebench.clean_marvel_kotlin.presentation.mvp.CharecterFragmentView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [CharacterFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [CharacterFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class CharacterFragment : DialogFragment() {

    companion object {
        private val CHARACTER_ID = "character_id"

        fun newInstance(character_id: Int): CharacterFragment {
            val args = Bundle()
            args.putInt(CHARACTER_ID, character_id)
            val fragment = CharacterFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        var fragment_inflater: View = inflater.inflate(com.puzzlebench.clean_marvel_kotlin.R.layout.dialog_response, container, false)

        val getCharacterServiceByIdUseCase = GetCharacterByIdUseCase(CharacterServicesImpl())

        val presenter = CharacterFragmentPresenter(CharecterFragmentView(this),
                getCharacterServiceByIdUseCase, arguments.getInt(CHARACTER_ID))

        presenter.init()
        return fragment_inflater
    }

}
