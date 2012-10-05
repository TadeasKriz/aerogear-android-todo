/*
 * JBoss, Home of Professional Open Source
 * Copyright 2012, Red Hat, Inc., and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.aerogear.proto.todos.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import org.aerogear.proto.todos.Constants;
import org.aerogear.proto.todos.R;
import org.aerogear.proto.todos.activities.MainActivity;
import org.aerogear.proto.todos.data.Task;
import org.aerogear.proto.todos.services.ToDoAPIService;

public class TaskFormFragment extends Fragment {

    private Task task;

    public TaskFormFragment() {
        this.task = new Task();
    }

    public TaskFormFragment(Task task) {
        this.task = task;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.form_task, null);

        TextView title = (TextView) view.findViewById(R.id.title);
        title.setText(getResources().getString(R.string.tasks));

        if( task.getId() != null ) {
            Button button = (Button) view.findViewById(R.id.buttonSave);
            button.setText(R.string.update);
        }

        final EditText name = (EditText)view.findViewById(R.id.name);
        final EditText description = (EditText)view.findViewById(R.id.description);

        name.setText(task.getTitle());
        description.setText(task.getDescription());

        Button buttonSave = (Button) view.findViewById(R.id.buttonSave);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameStr = name.getText().toString();
                if (nameStr.length() < 1) {
                    name.setError("Please enter a title");
                    return;
                }

                task.setTitle(nameStr);
                task.setDescription(description.getText().toString());

                Intent intent = new Intent(getActivity(), ToDoAPIService.class);
                intent.setAction(Constants.ACTION_POST_TASK);
                intent.putExtra(Constants.EXTRA_TASK, task);
                getActivity().startService(intent);
                ((MainActivity) getActivity()).showTaskList();
            }
        });

        Button buttonCancel = (Button) view.findViewById(R.id.buttonCancel);
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) getActivity()).showTaskList();
            }
        });

        return view;

    }
}

