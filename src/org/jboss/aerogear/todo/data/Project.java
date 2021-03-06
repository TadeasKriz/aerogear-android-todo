/**
 * JBoss, Home of Professional Open Source
 * Copyright Red Hat, Inc., and individual contributors
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

package org.jboss.aerogear.todo.data;

import org.jboss.aerogear.android.RecordId;

import android.os.Parcel;
import android.os.Parcelable;

public class Project implements Parcelable {
	
	@RecordId
	private String id;
	private String title;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return title;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Project project = (Project) o;
		if (title != null
				? !title.equals(project.title)
				: project.title != null)
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		return title != null ? title.hashCode() : 0;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel parcel, int flags) {
		parcel.writeString(id);
		parcel.writeString(title);
	}

	public static final Creator<Project> CREATOR = new Creator<Project>() {
		@Override
		public Project createFromParcel(Parcel parcel) {
			Project project = new Project();
			project.id = parcel.readString();
			project.title = parcel.readString();
			return project;
		}

		@Override
		public Project[] newArray(int size) {
			return new Project[size];
		}
	};
}
