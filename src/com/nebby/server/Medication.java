package com.nebby.server;

import java.util.Date;

public class Medication {

		private String name;
		private String time;
		public long timer = 0;
		public boolean checked = false;
		
		public Medication() {
			name = null;
			time= null;
		}
		
		public Medication(String n)
		{
			System.out.println(n);
			name = n.split(":")[0];
			time = n.split(":")[1];
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getTime() {
			return time;
		}

		public void setTime(String date) {
			this.time = date;
		}

		@Override
		public String toString() {
			return "Medication [name=" + name + ", time=" + time + ", timer=" + timer + ", checked=" + checked + "]";
		}
		
}
