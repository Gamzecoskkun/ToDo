package com.gamzecoskun.todo.ui.newAndEdit

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.gamzecoskun.todo.databinding.FragmentNewAndEditBinding
import com.gamzecoskun.todo.model.Priority
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewAndEditFragment : Fragment() {
    private var _binding: FragmentNewAndEditBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<NewAndEditViewModel>()
    private val args by navArgs<NewAndEditFragmentArgs>()
    private var currentPriorityIndex = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewAndEditBinding.inflate(inflater, container, false)

        if (args.toDoId==-1){
            binding.icDelete.isVisible=false
            (activity as AppCompatActivity?)!!.supportActionBar!!.title="New To-Do"
        }else{
            (activity as AppCompatActivity?)!!.supportActionBar!!.title="Edit To-Do"
            viewModel.getToDo(args.toDoId)
        }

        initializeView()

        viewModel.toDoModel.observe(viewLifecycleOwner){
            binding.titTitle.setText(it.title)
            binding.titDescription.setText(it.description)
            binding.acPriorty.setText(it.priority?.name,false)
            binding.checkBox.isChecked=it.isChecked==true

            currentPriorityIndex=when(it.priority){
                Priority.HIGH->0
                Priority.MEDIUM->1
                else->2
            }
        }
        return binding.root
    }

    private fun initializeView(){
        binding.btnSave.setOnClickListener {
            hundelSaveButton()
        }

        binding.acPriorty.setAdapter(
            ArrayAdapter(
                requireContext(),
                android.R.layout.simple_list_item_1,
                arrayOf(Priority.HIGH.name, Priority.MEDIUM.name, Priority.LOW.name)
            )
        )

        binding.acPriorty.setOnItemClickListener { _, _, index, _ ->
            currentPriorityIndex = index
        }

        binding.icDelete.setOnClickListener{
            viewModel.deleteToDo()

            Toast.makeText(requireContext(),"Successfully Deleted",Toast.LENGTH_SHORT).show()
            findNavController().popBackStack()
        }
    }

    private fun hundelSaveButton() {
        val title = binding.titTitle.text.toString()
        val description = binding.titDescription.text.toString()

        val priorty = when (currentPriorityIndex) {
            0 -> Priority.HIGH
            1 -> Priority.MEDIUM
            else -> Priority.LOW
        }

        if(args.toDoId==-1){
            viewModel.insertToDo(title, description, binding.checkBox.isChecked, priorty)

        }else{
            viewModel.updatedToDo(title,description,binding.checkBox.isChecked,priorty)
        }

        Toast.makeText(requireContext(), "Successfully Saved", Toast.LENGTH_SHORT).show()
        findNavController().popBackStack()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}