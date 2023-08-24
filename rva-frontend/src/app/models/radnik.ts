import { obrazovanje } from "./obrazovanje";
import { sektor } from "./sektor";

export class radnik{
    idRadnik!: number;
    brojLk!: number;
    ime!: string;
    prezime!: string;
    obrazovanje!: obrazovanje;
    sektor!: sektor;
}